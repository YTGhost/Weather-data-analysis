package com.hihia.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/8 23:32
 * @email 18221221@bjtu.edu.cn
 * 与数据库中的users表对应
 */
public class UserInfo implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;
    private List<Dept> depts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Dept> getDepts() {
        return depts;
    }

    public void setDepts(List<Dept> depts) {
        this.depts = depts;
    }
}
