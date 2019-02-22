package cn.newstrength.user.entity;

import cn.newstrength.core.entity.BaseObj;

import java.util.Date;

public class UserObj extends BaseObj {
    private Integer userId;
    private String userName;
    private String passWord;
    private int errLoginCount;
    private Date lastEditPassWordTime;
    private String accountStatue;
    private String salt;


    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public String getAccountStatue() {
        return accountStatue;
    }

    public void setAccountStatue(String accountStatue) {
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
