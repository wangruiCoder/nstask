package cn.newstrength.core.constant;

/**
 * 系统级别的错误定义，定义日常使用的成功跟失败
 * @author 王瑞
 */
public enum SystemBusinessExceptionCodeEnum {
    SUCCESS_CODE("0000",""),
    ERROR_CODE("9999","系统错误，请联系管理员");

    //业务编码
    private String businessCode;
    //业务描述
    private String desc;

    SystemBusinessExceptionCodeEnum(String businessCode, String desc){
        this.businessCode = businessCode;
        this.desc = desc;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public String getDesc() {
        return desc;
    }

}
