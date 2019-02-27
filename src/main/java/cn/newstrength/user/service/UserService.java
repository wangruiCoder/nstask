package cn.newstrength.user.service;


import cn.newstrength.user.entity.UserBO;

public interface UserService{
    UserBO queryByUserName(UserBO userBo);
}
