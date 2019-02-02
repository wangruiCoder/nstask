package cn.newstrength.service.impl;

import cn.core.service.impl.BaseServiceImpl;
import cn.newstrength.dao.UserDao;
import cn.newstrength.entity.UserObj;
import cn.newstrength.service.UserService;
import cn.newstrength.util.PasswordPBKDF2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<UserObj> implements UserService {

    private Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
        super.setBaseDao(userDao);
    }

    public int add(UserObj userObj){
        int result = 0 ;
        try {
            String salt = PasswordPBKDF2.generateSalt();
            userObj.setSalt(salt);
            result = userDao.addOne(userObj);
        } catch (NoSuchAlgorithmException e) {
            logger.info("获取盐值失败!");
        }
        return result;
    }
}
