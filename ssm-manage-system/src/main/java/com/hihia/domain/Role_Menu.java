package com.hihia.domain;

import java.io.Serializable;

/**
 * @author 邓梁
 * @date 2020/7/12 16:24
 * @email 18221221@bjtu.edu.cn
 */
public class Role_Menu implements Serializable {

    private String roleId;
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "Role_Menu{" +
                "roleId='" + roleId + '\'' +
                ", menuId='" + menuId + '\'' +
                '}';
    }
}
