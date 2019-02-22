package cn.newstrength.user.service.impl;

import cn.newstrength.core.service.AbstractLog4j2Service;
import cn.newstrength.core.service.BaseInsertOneObjectService;
import cn.newstrength.core.service.BaseUpdateOneObjectService;
import cn.newstrength.user.dao.UserDao;
import cn.newstrength.user.entity.UserObj;
import cn.newstrength.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "userService")
public class UserServiceImpl extends AbstractLog4j2Service<UserServiceImpl>
        implements UserService
        , BaseInsertOneObjectService<UserObj>
        , BaseUpdateOneObjectService<UserObj> {

    @Autowired
    private UserDao userDao;

    @Override
    public int insertOne(UserObj userObj) {
        super.logger.info(userObj.toString());
        return 1;
    }

    @Override
    public int updateOne(UserObj userObj) {
        return userDao.updateOneByObject(userObj);
    }
}
