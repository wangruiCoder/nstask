package cn.newstrength.user.service.impl;

import cn.newstrength.common.util.PasswordPBKDF2;
import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.exception.BusinessException;
import cn.newstrength.core.service.AbstractLog4j2Service;
import cn.newstrength.core.service.BaseInsertOneService;
import cn.newstrength.core.service.BaseUpdateOneService;
import cn.newstrength.user.dao.UserDao;
import cn.newstrength.user.entity.UserBO;
import cn.newstrength.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;


@Service(value = "userService")
public class UserServiceImpl extends AbstractLog4j2Service<UserServiceImpl>
        implements UserService
        , BaseInsertOneService<UserBO>
        , BaseUpdateOneService<UserBO> {

    @Autowired
    private UserDao userMapper;

    @Override
    public int insertOne(UserBO userObj) throws BusinessException {
        int result ;
        Map<String,String> quaryCondition= new HashMap<String,String>();
        quaryCondition.put("userName",userObj.getUserName());

        UserBO queryUserBo = userMapper.queryByUserName(quaryCondition);
        if (queryUserBo != null) {
            throw new BusinessException("9998","用户已存在");
        }

        try {
            //设置盐值
            String salt = PasswordPBKDF2.generateSalt();
            userObj.setSalt(salt);
            userObj.setPassWord(PasswordPBKDF2.getEncryptedPassword(userObj.getPassWord(),salt));
            result = userMapper.insertOne(userObj);
        } catch (NoSuchAlgorithmException e) {
            logger.error("获取盐值异常!");
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE);
        } catch (InvalidKeySpecException e){
            logger.error("加密密码异常!");
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE);
        } catch (Exception e) {
            logger.error("注册用户失败，错误信息："+e.getMessage());
            throw new BusinessException("9999","注册用户失败，请联系管理员!");
        }

        return result;
    }

    @Override
    public int updateOne(UserBO userObj) {
        return userMapper.updateOneByObject(userObj);
    }
}
