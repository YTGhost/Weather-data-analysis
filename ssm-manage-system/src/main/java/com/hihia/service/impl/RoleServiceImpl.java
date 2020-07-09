package com.hihia.service.impl;

import com.hihia.dao.RoleDao;
import com.hihia.domain.Role;
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
}
