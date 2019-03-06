package cn.newstrength.user.constant;

public enum UserMoudleRedisKeyEnum {
    ERROR_LOGIN_COUNT_PREFIX("ERROR_LOGIN_COUNT_"),
    USER_LOGIN_SESSION_PREFIX("USER_LOGIN_SESSION_");

    private String key;
    UserMoudleRedisKeyEnum(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
