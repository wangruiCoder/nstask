package cn.newstrength.user.service.impl;

import cn.newstrength.core.encryption.jwt.CalendarFieldEnum;
import cn.newstrength.core.encryption.jwt.JwtHelpor;
import cn.newstrength.core.encryption.password.PasswordPBKDF2;
import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.exception.BusinessException;
import cn.newstrength.core.service.AbstractLog4j2Service;
import cn.newstrength.core.service.BaseInsertOneService;
import cn.newstrength.core.service.BaseUpdateOneService;
import cn.newstrength.user.dao.UserDao;
import cn.newstrength.user.entity.UserBO;
import cn.newstrength.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service(value = "userService")
@Primary
public class UserServiceImpl extends AbstractLog4j2Service<UserServiceImpl>
        implements UserService,
        BaseInsertOneService<UserBO>,
        BaseUpdateOneService<UserBO> {

    @Autowired
    private UserDao userMapper;

    @Override
    public int insertOne(UserBO userObj) throws BusinessException {
        logger.info("用户注册开始");
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

        logger.info("用户注册结束");
        return result;
    }

    @Override
    public int updateOne(UserBO userObj) {
        return userMapper.updateOneByObject(userObj);
    }

    @Override
    public String queryByUserName(String userName, String passWord, String code) throws BusinessException {
        Map<String,String> quaryCondition= new HashMap<String,String>();
        quaryCondition.put("userName",userName);
        UserBO queryUserBo = userMapper.queryByUserName(quaryCondition);
        String token = "";

        try {
            String passWordPBKDF2 = PasswordPBKDF2.getEncryptedPassword(passWord,queryUserBo.getSalt());
            if (passWordPBKDF2.equals(queryUserBo.getPassWord())) {
                Map<String,Object> claim = new HashMap<String, Object>();
                claim.put("userId",queryUserBo.getUserId());
                claim.put("userName",queryUserBo.getUserName());
                token = JwtHelpor.getInstance().createToken(claim,SignatureAlgorithm.HS256,new Date(),CalendarFieldEnum.DEFAULT,30);
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("密码加密错误");
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE);
        } catch (InvalidKeySpecException e) {
            logger.error("密码加密错误");
            throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE);
        }

        logger.info(token);
        return token;
    }
}
