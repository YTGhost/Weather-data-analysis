package com.hihia.service;

import com.hihia.domain.Role;
import com.hihia.domain.User_Role;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:25
 * @email 18221221@bjtu.edu.cn
 */
public interface RoleService {

    /**
     * 查询指定用户id的角色
     * @param id
     * @return
     */
    public List<Role> findRoleByUserId(String id);

    /**
     * 获取角色列表
     * @return
     */
    public List<Role> findAll();

    /**
     * 通过角色id来获取角色该角色所有的权限
     * @param id
     * @return
     */
    public List<Role> findRoleByRoleId(String id);

    /**
     * 检查用户是否已经有角色
     * @param userId
     * @return
     */
    public User_Role checkRole(String userId);

    /**
     * 给没有角色的用户分配角色
     * @param userId
     * @param roleId
     */
    public void assignRole(String userId, String roleId);


    public void changeRole(String userId, String roleId);
}
