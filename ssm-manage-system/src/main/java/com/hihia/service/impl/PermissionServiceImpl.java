package com.hihia.service.impl;

import com.hihia.dao.PermissionDao;
import com.hihia.domain.Menu;
import com.hihia.domain.Permission;
import com.hihia.domain.Role_Permission;
import com.hihia.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 17:49
 * @email 18221221@bjtu.edu.cn
 */

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findPermissionByRoleId(String id) {
        return permissionDao.findPermissionByRoleId(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void assignPower(String roleId, String permissionId) {
        Role_Permission role_permission = permissionDao.checkPermission(roleId, permissionId);
        if(role_permission == null){
            permissionDao.assignPower(roleId, permissionId);
        }
    }

    @Override
    public void deletePower(String roleId, String permissionId) {
        Role_Permission role_permission = permissionDao.checkPermission(roleId, permissionId);
        if(role_permission != null){
            permissionDao.deletePower(roleId, permissionId);
        }
    }
}
