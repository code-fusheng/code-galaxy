package xyz.fusheng.admin.exam.core.service;

import xyz.fusheng.core.model.dto.OptionDto;

public interface OptionService{

    /**
     * 添加试题选项
     * @param optionDto
     */
    void saveOption(OptionDto optionDto);
}
