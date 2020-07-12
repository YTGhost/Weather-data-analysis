package com.hihia.service.impl;

import com.hihia.dao.MenusDao;
import com.hihia.dao.PermissionDao;
import com.hihia.dao.RoleDao;
import com.hihia.domain.Menu;
import com.hihia.domain.Permission;
import com.hihia.domain.Role;
import com.hihia.domain.User_Role;
import com.hihia.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:35
 * @email 18221221@bjtu.edu.cn
 */

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private MenusDao menusDao;

    @Override
    public List<Role> findRoleByUserId(String id) {
        return roleDao.findRoleByUserId(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findRoleByRoleId(String id) {
        return roleDao.findRoleByRoleId(id);
    }

    @Override
    public User_Role checkRole(String userId) {
        return roleDao.checkRole(userId);
    }

    @Override
    public void assignRole(String userId, String roleId) {
        roleDao.assignRole(userId, roleId);
    }

    @Override
    public void changeRole(String userId, String roleId) {
        roleDao.changeRole(userId, roleId);
    }

    @Override
    public void createRole(String roleName) {
        roleDao.createRole(roleName);
    }

    @Override
    public Role checkRoleName(String roleName) {
        return roleDao.checkRoleName(roleName);
    }

    @Override
    public void modifyRole(String id, String roleName) {
        roleDao.modifyRole(id, roleName);
    }

    @Override
    public void deleteRole(String id) {
        roleDao.deleteRoleAssoUser(id);
        roleDao.deleteRoleAssoMenu(id);
        roleDao.deleteRoleAssoPermission(id);
        roleDao.deleteRoleInfo(id);
    }

    @Override
    public Role findRoleById(String id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String id) {
        return permissionDao.findPermissionByRoleId(id);
    }

    @Override
    public List<Menu> findMenuByRoleId(String id) {
        return menusDao.findMenuByRoleId(id);
    }
}
