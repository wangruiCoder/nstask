package cn.newstrength.user.pattern.state;

import cn.newstrength.user.entity.UserBO;
import org.springframework.data.redis.core.RedisTemplate;

public class VerifyContext {

    private AbstractVerifyState verifyState;
    private RedisTemplate redisTemplate;
    private UserBO userBO;
    private String redisErrorLoginKey ;
    private String attemptedPassword;
    private Integer errorInfo;

    public VerifyContext(RedisTemplate redisTemplate, UserBO userBO,String attemptedPassword) {
        this.redisTemplate = redisTemplate;
        this.userBO = userBO;
        this.redisErrorLoginKey = redisErrorLoginKey;
        this.attemptedPassword = attemptedPassword;
    }

    public Integer getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(Integer errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getAttemptedPassword() {
        return attemptedPassword;
    }

    public void setAttemptedPassword(String attemptedPassword) {
        this.attemptedPassword = attemptedPassword;
    }

    public String getRedisErrorLoginKey() {
        return redisErrorLoginKey;
    }

    public void setRedisErrorLoginKey(String redisErrorLoginKey) {
        this.redisErrorLoginKey = redisErrorLoginKey;
    }

    public AbstractVerifyState getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(AbstractVerifyState verifyState) {
        this.verifyState = verifyState;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public UserBO getUserBO() {
        return userBO;
    }

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }

    public void startVerify(){
        verifyState.verify(this);
    }

    public void setVerifyStateAndStartVerify(AbstractVerifyState verifyState){
        this.verifyState = verifyState;
        this.startVerify();
    }
}
