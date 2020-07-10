package com.hihia.domain;

import java.io.Serializable;

/**
 * @author 邓梁
 * @date 2020/7/11 0:20
 * @email 18221221@bjtu.edu.cn
 */
public class User_Dept implements Serializable {

    private String userId;
    private String deptId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "User_Dept{" +
                "userId='" + userId + '\'' +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}
