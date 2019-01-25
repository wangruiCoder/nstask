package cn.newstrength.service.impl;

import cn.core.service.impl.BaseServiceImpl;
import cn.newstrength.dao.UserDao;
import cn.newstrength.entity.UserObj;
import cn.newstrength.service.UserService;

import javax.annotation.Resource;

public class UserServiceImpl extends BaseServiceImpl<UserObj> implements UserService {

    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
        super.setBaseDao(userDao);
    }

}
