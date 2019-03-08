package cn.newstrength.user.pattern.state;

import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.exception.BusinessException;

public class NoUserVerifyState extends AbstractVerifyState {
    @Override
    protected void verify(VerifyContext verifyContext) throws BusinessException, RuntimeException {
        if (verifyContext.getUserBO() == null){
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode(),"用户不存在");
        }

    }
}
