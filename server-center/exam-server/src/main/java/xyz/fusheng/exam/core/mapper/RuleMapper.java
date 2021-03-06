package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.core.model.base.PageData;
import xyz.fusheng.exam.model.entity.Rule;
import xyz.fusheng.exam.model.vo.RuleVo;

import java.util.List;

@Mapper
public interface RuleMapper extends BaseMapper<Rule> {

    /**
     * 分页查询规则
     * @param page
     * @return
     */
    List<RuleVo> getByPage(PageData<RuleVo> page);

    /**
     * 统计分页规则总数
     * @param page
     * @return
     */
    int getCountByPage(PageData<RuleVo> page);
}
