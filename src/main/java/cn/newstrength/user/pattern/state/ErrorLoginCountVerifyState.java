package cn.newstrength.user.pattern.state;

import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.exception.BusinessException;
import cn.newstrength.user.constant.UserMoudleRedisKeyEnum;

public class ErrorLoginCountVerifyState extends AbstractVerifyState {
    @Override
    protected void verify(VerifyContext verifyContext) throws BusinessException, RuntimeException {
        String errorRedisKEy = UserMoudleRedisKeyEnum.ERROR_LOGIN_COUNT_PREFIX.getKey().concat(String.valueOf(verifyContext.getUserBO().getUserId()));
        verifyContext.setRedisErrorLoginKey(errorRedisKEy);

        Integer errorInfo = (Integer) verifyContext.getRedisTemplate().opsForValue().get(errorRedisKEy);

        if (errorInfo != null && errorInfo.intValue() >= 5) {
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode(),"半个小时内错误登录次数超过5次");
        } else {
            verifyContext.setErrorInfo(errorInfo);
        }
    }
}
