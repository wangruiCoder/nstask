package cn.newstrength.util;

import cn.newstrength.entity.UserObj;

public class MainTest {
    public static void main(String[] args){
        UserObj userObj = new UserObj();
        userObj.setUserId(1);
        userObj.setCreatorId(1);
        userObj.setPassWord("cyjj");
        userObj.setUserName("wangrui");

        System.out.println(userObj.toString());
    }
}
