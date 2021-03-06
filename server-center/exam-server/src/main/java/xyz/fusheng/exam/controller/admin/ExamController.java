package xyz.fusheng.exam.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.core.enums.ResultEnum;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.core.model.vo.ResultVo;
import xyz.fusheng.core.utils.StringUtils;
import xyz.fusheng.exam.core.service.ExamService;
import xyz.fusheng.exam.model.dto.ExamDto;
import xyz.fusheng.exam.model.vo.ExamVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: ExamController
 * @Author: code-fusheng
 * @Date: 2021/5/11 2:44 下午
 * @Version: 1.0
 * @Description: 考试控制类
 */

@RestController
@RequestMapping("/admin/exam")
@Api(tags = "考试管理", value = "考试接口管理")
public class ExamController {

    @Resource
    private ExamService examService;

    @ApiOperation("添加考试")
    @PostMapping("/saveExam")
    public ResultVo<Object> saveExam(@RequestBody @Validated ExamDto examDto) {
        examService.saveExam(examDto);
        return new ResultVo<>("操作提示: 添加考试成功!");
    }

    @ApiOperation(value = "分页查询考试列表")
    @PostMapping("/getExamByPage")
    public ResultVo<PageData<ExamVo>> getExamByPage(@RequestBody PageData<ExamVo> page) {
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 考试名、创建时间、更新时间
            String[] sortColumns = {"exam_name", "start_time", "end_time", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new ResultVo<>(ResultEnum.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = examService.getExamVoByPage(page);
        return new ResultVo<>("操作成功: 分页查询考试视图详情!", page);
    }






}
