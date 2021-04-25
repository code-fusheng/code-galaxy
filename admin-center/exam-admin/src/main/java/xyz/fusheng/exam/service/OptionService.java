package xyz.fusheng.exam.service;

import xyz.fusheng.model.dto.OptionDto;

public interface OptionService{

    /**
     * 添加试题选项
     * @param optionDto
     */
    void saveOption(OptionDto optionDto);
}
