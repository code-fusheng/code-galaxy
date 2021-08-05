package xyz.fusheng.bill.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import xyz.fusheng.bill.common.annotation.UserInfo;
import xyz.fusheng.bill.core.service.TransRecordService;
import xyz.fusheng.bill.model.dto.TransRecordDto;
import xyz.fusheng.bill.model.vo.TransRecordVo;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.base.Page;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: TransRecordController
 * @Author: code-fusheng
 * @Date: 2021/7/6 上午9:14
 * @Version: 1.0
 * @Description: 消费记录控制类
 */

@Api("消费记录控制类")
@RestController
@RequestMapping("/admin/transRecord")
public class AdminTransRecordController {

    @Resource
    private TransRecordService transRecordService;

    @ApiOperation("添加消费记录")
    @PostMapping("/saveTransRecord")
    public ResultVo<Object> saveTransRecord(@RequestBody TransRecordDto transRecordDto, @ApiIgnore @UserInfo SelfUser userInfo) {
        transRecordDto.setCreatorId(userInfo.getUserId());
        transRecordDto.setCreatorName(userInfo.getUsername());
        transRecordService.saveTransRecord(transRecordDto);
        return new ResultVo<>("操作成功: 添加消费记录");
    }

    @ApiOperation("分页查询消费记录列表")
    @PostMapping("/pageTransRecord")
    public ResultVo<Page<TransRecordVo>> pageTransRecord(@RequestBody Page<TransRecordVo> page) {
        if (StringUtils.isNotBlank(page.getSortColumn())) {
            // 创建时间、更新时间
            String[] sortColumns = {"created_time", "updated_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(page.getSortColumn().toLowerCase())) {
                return new ResultVo<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = transRecordService.pageTransRecord(page);
        return new ResultVo<>("操作成功: 分页查询消费记录列表", page);
    }

}
