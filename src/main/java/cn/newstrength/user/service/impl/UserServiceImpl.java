package cn.newstrength.user.service.impl;

import cn.newstrength.core.service.AbstractLog4j2Service;
import cn.newstrength.core.service.BaseInsertOneService;
import cn.newstrength.core.service.BaseUpdateOneService;
import cn.newstrength.user.dao.UserDao;
import cn.newstrength.user.entity.UserBO;
import cn.newstrength.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "userService")
public class UserServiceImpl extends AbstractLog4j2Service<UserServiceImpl>
        implements UserService
        , BaseInsertOneService<UserBO>
        , BaseUpdateOneService<UserBO> {

    @Autowired
    private UserDao userDao;

    @Override
    public int insertOne(UserBO userObj) {
        super.logger.info(userObj.toString());
        return 1;
    }

    @Override
    public int updateOne(UserBO userObj) {
        return userDao.updateOneByObject(userObj);
    }
}
