package com.hihia.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 0:04
 * @email 18221221@bjtu.edu.cn
 */
public class Dept implements Serializable {
    private Integer id;
    private String deptName;
    private List<UserInfo> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", users=" + users +
                '}';
    }
}
