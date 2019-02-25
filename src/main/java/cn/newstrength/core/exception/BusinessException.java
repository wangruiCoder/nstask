package cn.newstrength.core.exception;

import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;

/**
 * 自定义的系统业务异常类，用于程序正常运行时在业务阶段产生的错误的异常处理
 * @author 王瑞
 * @see java.lang.Exception
 */
public class BusinessException extends RuntimeException{
    private String exceptionCode;

    /**
     * 系统异常构造器
     * @param exceptionCode 异常码
     * @param exceptionMessage 异常信息
     * @see java.lang.Exception#Exception(String)
     */
    public BusinessException(String exceptionCode, String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 支持枚举类型的系统编码错误
     * @param systemBusinessCodeEnum 设置的枚举异常编码信息
     */
    public BusinessException(SystemBusinessExceptionCodeEnum systemBusinessCodeEnum){
        super(systemBusinessCodeEnum.getDesc());
        this.exceptionCode = systemBusinessCodeEnum.getBusinessCode();
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
