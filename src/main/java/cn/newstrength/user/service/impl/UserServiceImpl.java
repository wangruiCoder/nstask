package cn.newstrength.user.service.impl;

import cn.newstrength.core.constant.SystemBusinessExceptionCodeEnum;
import cn.newstrength.core.encryption.jwt.CalendarFieldEnum;
import cn.newstrength.core.encryption.jwt.JwtHelpor;
import cn.newstrength.core.encryption.password.PasswordPBKDF2;
import cn.newstrength.core.exception.BusinessException;
import cn.newstrength.core.service.AbstractLog4j2Service;
import cn.newstrength.core.service.BaseInsertOneService;
import cn.newstrength.core.service.BaseUpdateOneService;
import cn.newstrength.user.constant.UserMoudleRedisKeyEnum;
import cn.newstrength.user.dao.UserDao;
import cn.newstrength.user.entity.UserBO;
import cn.newstrength.user.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service(value = "userService")
@Primary
public class UserServiceImpl extends AbstractLog4j2Service<UserServiceImpl>
        implements UserService,
        BaseInsertOneService<UserBO>,
        BaseUpdateOneService<UserBO> {

    //错误登录次数限时范围
    private static final Long LIMIT_TIME = 30L;
    private static final int ERROR_LOGIN_COUNT = 5;

    @Autowired
    private UserDao userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

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

            String errorRedisKEy = UserMoudleRedisKeyEnum.ERROR_LOGIN_COUNT_PREFIX.getKey().concat(String.valueOf(queryUserBo.getUserId()));
            Integer errorInfo = (Integer) redisTemplate.opsForValue().get(errorRedisKEy);

            if (errorInfo != null && errorInfo.intValue() >= 5) {
                throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode(),"半个小时内错误登录次数超过5次");
            }

            if (!PasswordPBKDF2.authenticate(passWord,queryUserBo.getPassWord(),queryUserBo.getSalt())) {
                int loginCount = 0;
                if (errorInfo == null) {
                    loginCount = ERROR_LOGIN_COUNT;
                    redisTemplate.opsForValue().set(errorRedisKEy,1,LIMIT_TIME,TimeUnit.MINUTES);
                } else {
                    loginCount = ERROR_LOGIN_COUNT - errorInfo.intValue();
                    redisTemplate.opsForValue().increment(errorRedisKEy);
                }
                throw new BusinessException(SystemBusinessExceptionCodeEnum.ERROR_CODE.getBusinessCode(),"密码错误,剩余可登录次数:"+loginCount);
            }

            Map<String,Object> claim = new HashMap<String, Object>();
            claim.put("userId",queryUserBo.getUserId());
            claim.put("userName",queryUserBo.getUserName());
            token = JwtHelpor.getInstance().createToken(claim,SignatureAlgorithm.HS256,new Date(),CalendarFieldEnum.DEFAULT,30);

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
