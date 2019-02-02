package cn.newstrength.util;

import cn.newstrength.entity.UserObj;

import java.security.NoSuchAlgorithmException;

public class MainTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        String a = PasswordPBKDF2.generateSalt();
        System.out.println(a);
    }
}
