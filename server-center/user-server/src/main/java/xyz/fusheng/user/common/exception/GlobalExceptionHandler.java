package xyz.fusheng.user.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.fusheng.core.enums.ResultEnum;
import xyz.fusheng.core.exception.BusinessException;
import xyz.fusheng.core.model.vo.ResultVo;

import java.sql.SQLIntegrityConstraintViolationException;


/**
 * @FileName: GlobalExceptionHandler
 * @Author: code-fusheng
 * @Date: 2021/4/12 4:52 下午
 * @Version: 1.0
 * @Description: 全局异常处理器
 * @RestControllerAdvice 包含 @ControllerAdvice注解和@ResponseBody注解
 *
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BindException.class)
    public ResultVo validationExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = "";
        for (FieldError  fieldError : bindingResult.getFieldErrors()) {
            errorMessage += fieldError.getDefaultMessage() + "";
        }
        return ResultVo.error(errorMessage);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVo validationExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = "";
        for (FieldError  fieldError : bindingResult.getFieldErrors()) {
            errorMessage += fieldError.getDefaultMessage() + "";
        }
        return ResultVo.error(errorMessage);
    }

    /**
     * 自定义业务异常全局处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResultVo validationExceptionHandler(BusinessException e) {
        logger.error("业务异常,异常信息:", e);
        return ResultVo.error(ResultEnum.BUSINESS_ERROR.getCode(), ResultEnum.BUSINESS_ERROR.getMsg() + ":" + e.getMessage());
    }

    // TODO 仅用于开发
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResultVo validationExceptionHandler(SQLIntegrityConstraintViolationException e) {
        return ResultVo.error(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

}
