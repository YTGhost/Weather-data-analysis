package com.hihia.dao;

import com.hihia.domain.Permission;
import com.hihia.domain.Role_Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 16:06
 * @email 18221221@bjtu.edu.cn
 */

@Repository
public interface PermissionDao {

    /**
     * 根据roleId查询当前角色的全部权限
     * @param id
     * @return
     */
    @Select("select * from permissions where id in (select permissionId from roles_permissions where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id);

    @Select("select * from permissions")
    public List<Permission> findAll();

    @Select("select * from roles_permissions where roleId=#{roleId} and permissionId=#{permissionId}")
    public Role_Permission checkPermission(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Insert("insert into roles_permissions (roleId, permissionId) value(#{roleId}, #{permissionId})")
    public void assignPower(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Delete("delete from roles_permissions where roleId=#{roleId} and permissionId=#{permissionId}")
    public void deletePower(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
