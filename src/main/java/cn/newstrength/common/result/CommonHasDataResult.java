package cn.newstrength.common.result;

import cn.newstrength.core.result.Result;

/**
 * 统一结果处理类
 * @author 王瑞
 * @see cn.newstrength.core.result.Result
 */
public class CommonHasDataResult extends Result {
    private Object data;

    /**
     * 无参构造
     */
    public CommonHasDataResult() {
    }

    /**
     * 错误码跟错误信息构造
     * @param errorCode 错误码
     * @param message 错误信息
     * @see cn.newstrength.core.result.Result#Result(String, String)
     */
    public CommonHasDataResult(String errorCode, String message) {
        super(errorCode,message);
    }

    /**
     * 错误码跟错误信息构造+返回数据
     * @param errorCode 错误码
     * @param message 错误信息
     * @param data 返回数据
     * @see cn.newstrength.core.result.Result#Result(String, String)
     */
    public CommonHasDataResult(String errorCode, String message, Object data) {
        super(errorCode,message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
