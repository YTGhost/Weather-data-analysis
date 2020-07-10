package com.hihia.dao;

import com.hihia.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}
