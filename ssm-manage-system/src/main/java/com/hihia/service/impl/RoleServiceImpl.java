package com.hihia.service.impl;

import com.hihia.dao.RoleDao;
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
}
