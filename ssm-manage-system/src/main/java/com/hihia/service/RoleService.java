package com.hihia.service;

import com.hihia.domain.Menu;
import com.hihia.domain.Permission;
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

    /**
     * 更改用户角色
     * @param userId
     * @param roleId
     */
    public void changeRole(String userId, String roleId);

    /**
     * 创建角色
     * @param roleName
     */
    public void createRole(String roleName);

    /**
     * 检查角色是否已创建
     * @param roleName
     * @return
     */
    public Role checkRoleName(String roleName);

    /**
     * 修改角色信息
     * @param id
     * @param roleName
     */
    public void modifyRole(String id, String roleName);

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id);

    /**
     * 通过角色id查找角色信息
     * @param id
     * @return
     */
    public Role findRoleById(String id);

    /**
     * 通过角色id获得权限列表
     * @param id
     * @return
     */
    public List<Permission> findPermissionByRoleId(String id);

    /**
     * 通过角色id获得菜单列表
     * @param id
     * @return
     */
    public List<Menu> findMenuByRoleId(String id);
}
