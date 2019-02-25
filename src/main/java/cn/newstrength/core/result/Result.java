package cn.newstrength.core.result;

/**
 * 结果集父类，抽象返回结果必须有编码跟信息
 * @author 王瑞
 *
 */
public class Result {

    protected String errorCode;
    protected String message;


    public Result(){}

    /**
     * 入参构造函数，设置返回码跟返回结果
     * @param errorCode 返回码
     * @param message 返回信息
     */
    public Result(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
