package xyz.fusheng.test.controller.api;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.vo.ResultVo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @FileName: ApiTestController
 * @Author: code-fusheng
 * @Date: 2021/8/4 下午1:08
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/api/test")
@Slf4j
public class ApiTestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/testGetPort")
    public ResultVo<Object> testGetPort() {
        return ResultVo.success(port);
    }

    @GetMapping(value = "/download1")
    public void unDirectExport(HttpServletResponse response, Integer id) throws Exception {

        String fileName = "test2.zip";
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
        List<AAAA> list = get96Time();
        List<AAAA> list2 = get96Time();

        //按某个条件分组
        Map<String, List<AAAA>> map = new HashMap();
        map.put("test1.xls", list);
        map.put("test2.xls", list2);
        exportExcel(response, map);

    }

    private void exportExcel(HttpServletResponse response, Map<String, List<AAAA>> map) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        try {
            for (Map.Entry<String, List<AAAA>> entry : map.entrySet()) {
                String k = entry.getKey();
                List<AAAA> value = entry.getValue();
                //构建一个excel对象,这里注意type要是xls不能是xlsx,否则下面的写入后流会关闭,导致报错
                ExcelWriter excelWriter = EasyExcel.write().excelType(ExcelTypeEnum.XLS).build();
                //构建一个sheet页
                WriteSheet writeSheet = EasyExcel.writerSheet("薪资单").build();
                //构建excel表头信息
                WriteTable writeTable0 = EasyExcel.writerTable(0).head(AAAA.class).needHead(Boolean.TRUE).build();
                //将表头和数据写入表格
                excelWriter.write(value, writeSheet, writeTable0);

                //创建压缩文件
                ZipEntry zipEntry = new ZipEntry(k);
                zipOutputStream.putNextEntry(zipEntry);
                Workbook workbook = excelWriter.writeContext().writeWorkbookHolder().getWorkbook();
                //将excel对象以流的形式写入压缩流
                workbook.write(zipOutputStream);
            }
            zipOutputStream.flush();
        } catch (Exception e) {
            log.error("导XXX失败,原因" + e.getMessage());
            log.error(e.getMessage(), e);
            //抛出异常结束程序
            throw new BusinessException(20001, "数据导出接口异常");
        } finally {
            //关闭数据流，注意关闭的顺序
            zipOutputStream.close();
            outputStream.close();
        }
    }

    private List<AAAA> get96Time() {
        List<AAAA> res = new ArrayList<>(96);
        LocalDate localDate = LocalDate.now();
        LocalDateTime now = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0);
        for (int i = 1; i < 96; i++) {
            AAAA a = new AAAA();
            String format = now.plusMinutes(15 * i).format(DateTimeFormatter.ofPattern("HH:mm"));
            a.setName(format);
            a.setAge(i);
            res.add(a);
        }
        return res;
    }

    @Data
    class AAAA {
        private Integer age;
        private String name;
    }
}
