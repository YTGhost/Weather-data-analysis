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
}
