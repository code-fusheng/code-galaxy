package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.model.dto.OptionDto;

public interface OptionService{

    /**
     * 添加试题选项
     * @param optionDto
     */
    void saveOption(OptionDto optionDto);
}
