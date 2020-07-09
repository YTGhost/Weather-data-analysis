package com.hihia.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 14:45
 * @email 18221221@bjtu.edu.cn
 */
public class Menu implements Serializable {
    private Integer id;
    private String menuName;
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
