package cn.newstrength.entity;

import cn.core.entity.BaseObj;

import java.util.Date;

public class UserObj extends BaseObj {
    private int userId;
    private String userName;
    private String passWord;
    private int errLoginCount;
    private Date lastEditPassWordTime;
    private int accountStatue;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getErrLoginCount() {
        return errLoginCount;
    }

    public void setErrLoginCount(int errLoginCount) {
        this.errLoginCount = errLoginCount;
    }

    public Date getLastEditPassWordTime() {
        return lastEditPassWordTime;
    }

    public void setLastEditPassWordTime(Date lastEditPassWordTime) {
        this.lastEditPassWordTime = lastEditPassWordTime;
    }

    public int getAccountStatue() {
        return accountStatue;
    }

    public void setAccountStatue(int accountStatue) {
        this.accountStatue = accountStatue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserObj{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", passWord='").append(passWord).append('\'');
        sb.append(", errLoginCount=").append(errLoginCount);
        sb.append(", lastEditPassWordTime=").append(lastEditPassWordTime);
        sb.append(", accountStatue=").append(accountStatue);
        sb.append(super.getBaseString());
        sb.append('}');
        return sb.toString();
    }
}