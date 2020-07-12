package com.hihia.domain;

import java.io.Serializable;

/**
 * @author 邓梁
 * @date 2020/7/12 16:45
 * @email 18221221@bjtu.edu.cn
 */
public class Role_Permission implements Serializable {

    private String roleId;
    private String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "Role_Permission{" +
                "roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                '}';
    }
}
