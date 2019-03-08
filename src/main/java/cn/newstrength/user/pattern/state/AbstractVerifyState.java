package cn.newstrength.user.pattern.state;

import cn.newstrength.core.exception.BusinessException;

public abstract class AbstractVerifyState {
    protected abstract void verify(VerifyContext verifyContext) throws BusinessException,RuntimeException;
}
