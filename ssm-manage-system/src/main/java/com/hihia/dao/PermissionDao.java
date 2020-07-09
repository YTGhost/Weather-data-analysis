package com.hihia.dao;

import com.hihia.domain.Permission;
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
}
