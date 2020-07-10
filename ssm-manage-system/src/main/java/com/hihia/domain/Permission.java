package com.hihia.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/8 23:55
 * @email 18221221@bjtu.edu.cn
 */
public class Permission implements Serializable {
    private Integer id;
    private String permissionName;
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
