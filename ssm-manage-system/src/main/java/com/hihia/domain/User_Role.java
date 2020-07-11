package com.hihia.domain;

import java.io.Serializable;

/**
 * @author 邓梁
 * @date 2020/7/10 22:39
 * @email 18221221@bjtu.edu.cn
 */
public class User_Role implements Serializable {

    private String userId;
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User_Role{" +
                "userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
