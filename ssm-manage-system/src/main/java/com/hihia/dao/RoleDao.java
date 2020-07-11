package com.hihia.dao;

import com.hihia.domain.Role;
import com.hihia.domain.User_Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:29
 * @email 18221221@bjtu.edu.cn
 */

@Repository
public interface RoleDao {

    /**
     * 根据用户id查询出所有对应的角色
     * @param userId
     * @return
     */
    @Select("select * from roles where id in (select roleId from users_roles where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.PermissionDao.findPermissionByRoleId")),
            @Result(property = "menus", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.MenusDao.findMenuByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId);

    /**
     * 获取角色列表
     * @return
     */
    @Select("select * from roles")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.PermissionDao.findPermissionByRoleId")),
            @Result(property = "menus", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.MenusDao.findMenuByRoleId"))
    })
    public List<Role> findAll();

    /**
     * 通过角色id来获取角色
     * @param id
     * @return
     */
    @Select("select * from roles where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.PermissionDao.findPermissionByRoleId")),
            @Result(property = "menus", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.MenusDao.findMenuByRoleId"))
    })
    public List<Role> findRoleByRoleId(String id);

    /**
     * 检查该用户是否已是该角色
     * @param userId
     * @param roleId
     * @return
     */
    @Select("select * from users_roles where userId=#{userId}")
    @Results({
            @Result(property = "userId", column = "userId"),
            @Result(property = "roleId", column = "roleId")
    })
    public User_Role checkRole(@Param("userId") String userId);

    /**
     * 分配给用户角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_roles (userId, roleId) values (#{userId}, #{roleId})")
    public void assignRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 更改用户角色
     * @param userId
     * @param roleId
     */
    @Update("update users_roles set roleId=#{roleId} where userId=#{userId}")
    public void changeRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 检查角色是否已经被创建
     * @param roleName
     * @return
     */
    @Select("select * from roles where roleName=#{roleName}")
    public Role checkRoleName(String roleName);

    /**
     * 创建角色
     * @param roleName
     */
    @Insert("insert into roles (roleName) values (#{roleName})")
    public void createRole(String roleName);
}
