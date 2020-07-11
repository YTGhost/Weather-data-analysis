package com.hihia.dao;

import com.hihia.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/8 23:33
 * @email 18221221@bjtu.edu.cn
 */

/**
 * 用户dao接口
 */
@Repository
public interface UserDao {

    /**
     * 查询指定用户名的用户信息
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.RoleDao.findRoleByUserId")),
            @Result(property = "depts", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.DeptDao.findDeptByUserId"))
    })
    public UserInfo findByUsername(String username);

    /**
     * 查询用户名是否被用过
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    public UserInfo checkUsername(String username);

    /**
     * 查询邮箱是否被用过
     * @param email
     * @return
     */
    @Select("select * from users where email=#{email}")
    public UserInfo checkEmail(String email);

    /**
     * 创建用户（基本信息）
     * @param userInfo
     */
    @Select("insert into users(username, password, email) value(#{username}, #{password}, #{email})")
    public void createUser(UserInfo userInfo);

    /**
     * 通过用户名来查找对应的id
     * @param username
     * @return
     */
    @Select("select id from users where username=#{username}")
    public String findIdByUsername(String username);

    /**
     * 通过用户id来删除用户基本信息
     * @param id
     */
    @Delete("delete from users where id=#{id}")
    public void deleteInfoById(String id);

    /**
     * 通过用户id来删除其与部门之间的所有关系
     * @param id
     */
    @Delete("delete from users_depts where userId=#{id}")
    public void deleteDeptLinkById(String id);

    /**
     * 通过用户id来删除其与角色之间的所有关系
     * @param id
     */
    @Delete("delete from users_roles where userId=#{id}")
    public void deleteRoleLinkById(String id);

    /**
     * 系统管理员获取所有用户信息
     * @return
     */
    @Select("select * from users")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.RoleDao.findRoleByUserId")),
            @Result(property = "depts", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.DeptDao.findDeptByUserId"))
    })
    public List<UserInfo> getUserInfoByRoot();

    /**
     * 普通管理员获取用户列表
     * @param userId
     * @return
     */
    @Select("select * from users")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.RoleDao.findRoleByUserId")),
            @Result(property = "depts", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.DeptDao.findDeptByUserId"))
    })
    public List<UserInfo> getUserInfoByAdmin(String userId);

    @Select("update users set username=#{username}, password=#{password}, email=#{email} where id=#{id}")
    public void modifyUserInfo(@Param("id") String id, @Param("username") String username, @Param("password") String password, @Param("email") String email);
}
