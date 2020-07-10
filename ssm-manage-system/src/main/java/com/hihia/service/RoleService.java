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
     * 检查给用户分配的角色该用户是否已有
     * @param userId
     * @param roleId
     * @return
     */
    public User_Role checkRole(String userId, String roleId);

    public void assignRole(String userId, String roleId);
}
