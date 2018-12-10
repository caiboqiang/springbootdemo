package com.springboot.springbootdemo.entity;

import java.io.Serializable;

/**
 * pojo 用户类
 */
public class UserInfo implements  Serializable {

    private Integer id;
    private String userAccount;
    private String userPhone;
    private Integer userGroupId;
    private Integer isfreeze;
    private String userPassword;
    private String userSalt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getIsfreeze() {
        return isfreeze;
    }

    public void setIsfreeze(Integer isfreeze) {
        this.isfreeze = isfreeze;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userGroupId=" + userGroupId +
                ", isfreeze=" + isfreeze +
                ", userPassword='" + userPassword + '\'' +
                ", userSalt='" + userSalt + '\'' +
                '}';
    }
}
