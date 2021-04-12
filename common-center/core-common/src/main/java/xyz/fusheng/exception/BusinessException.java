package xyz.fusheng.exception;

import lombok.Getter;
import xyz.fusheng.enums.ResultEnums;

/**
 * @FileName: BusinessException
 * @Author: code-fusheng
 * @Date: 2021/4/12 7:54 下午
 * @Version: 1.0
 * @Description: 自定义异常类 - 业务异常
 */

@Getter
public class BusinessException extends RuntimeException {

    /**
     * RuntimeException 运行时异常：是那些可能在 Java 虚拟机正常运行期间抛出的异常的超类。
     * 可能在执行方法期间抛出但未被捕获的RuntimeException 的任何子类都无需在 throws 子句中进行声明。
     * 它是Exception的子类。
     */

    private Integer errorCode = ResultEnums.BUSINESS_ERROR.getCode();

    public BusinessException(ResultEnums resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public BusinessException(ResultEnums resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public BusinessException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}
