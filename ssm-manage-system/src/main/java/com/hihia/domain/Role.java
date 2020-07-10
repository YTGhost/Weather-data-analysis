package com.hihia.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/8 23:55
 * @email 18221221@bjtu.edu.cn
 */
public class Role implements Serializable {
    private Integer id;
    private String roleName;
    private List<Permission> permissions;
    private List<UserInfo> users;
    private List<Menu> menus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", permissions=" + permissions +
                ", users=" + users +
                ", menus=" + menus +
                '}';
    }
}
