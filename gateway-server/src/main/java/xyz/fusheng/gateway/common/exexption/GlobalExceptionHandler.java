package xyz.fusheng.gateway.common.exexption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.fusheng.core.enums.ResultEnums;
import xyz.fusheng.core.model.vo.ResultVo;


/**
 * @FileName: GlobalExceptionHandler
 * @Author: code-fusheng
 * @Date: 2021/4/12 4:52 下午
 * @Version: 1.0
 * @Description: 全局异常处理器
 * @RestControllerAdvice 包含 @ControllerAdvice注解和@ResponseBody注解
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 断言统一异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultVo validationExceptionHandler(IllegalArgumentException e) {
        logger.error("断言异常,异常信息:", e);
        return ResultVo.error(ResultEnums.BUSINESS_ERROR.getCode(), ResultEnums.BUSINESS_ERROR.getMsg() + ":" + e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public ResultVo validationExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError  fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage());
        }
        logger.error("参数异常,异常信息:{}", errorMessage , e);
        return ResultVo.error(errorMessage.toString());
    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResultVo validationExceptionHandler(MethodArgumentNotValidException e) {
//        BindingResult bindingResult = e.getBindingResult();
//        StringBuilder errorMessage = new StringBuilder();
//        for (FieldError  fieldError : bindingResult.getFieldErrors()) {
//            errorMessage.append(fieldError.getDefaultMessage());
//        }
//        logger.error("参数异常,异常信息{}:", errorMessage, e);
//        return ResultVo.error(errorMessage.toString());
//    }
//
//    /**
//     * 自定义业务异常全局处理
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = BusinessException.class)
//    public ResultVo validationExceptionHandler(BusinessException e) {
//        logger.error("业务异常,异常信息:", e);
//        return ResultVo.error(ResultEnums.BUSINESS_ERROR.getCode(), ResultEnums.BUSINESS_ERROR.getMsg() + ":" + e.getMessage());
//    }
//
//    // TODO 仅用于开发
//    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
//    public ResultVo validationExceptionHandler(SQLIntegrityConstraintViolationException e) {
//        logger.error("数据库异常,异常信息:", e);
//        return ResultVo.error(ResultEnums.INTERNAL_SERVER_ERROR.getCode(), ResultEnums.INTERNAL_SERVER_ERROR.getMsg() + ":" + e.getMessage());
//    }

}
