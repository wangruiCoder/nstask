package cn.newstrength.user.pattern.state;

import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.encryption.password.PasswordPBKDF2;
import cn.newstrength.core.exception.BusinessException;
import cn.newstrength.user.entity.UserBO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.TimeUnit;

public class PasswordVerifyState extends AbstractVerifyState {
    private static final int ERROR_LOGIN_COUNT = 5;
    private static final Long LIMIT_TIME = 30L;
    @Override
    protected void verify(VerifyContext verifyContext) throws BusinessException, RuntimeException {
        int loginCount = 0;
        String passWord = verifyContext.getAttemptedPassword();
        UserBO queryUserBo = verifyContext.getUserBO();


        try {
            if (!PasswordPBKDF2.authenticate(passWord,queryUserBo.getPassWord(),queryUserBo.getSalt())) {

                if (verifyContext.getErrorInfo() == null) {
                    loginCount = ERROR_LOGIN_COUNT-1;
                    verifyContext.getRedisTemplate().opsForValue().set(verifyContext.getRedisErrorLoginKey(),1,LIMIT_TIME,TimeUnit.MINUTES);
                } else {
                    loginCount = ERROR_LOGIN_COUNT - (verifyContext.getErrorInfo().intValue()+1);
                    verifyContext.getRedisTemplate().opsForValue().increment(verifyContext.getRedisErrorLoginKey());
                }
                throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode(),"密码错误,剩余可登录次数:"+loginCount);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE);
        } catch (InvalidKeySpecException e) {
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE);
        }
    }
}
