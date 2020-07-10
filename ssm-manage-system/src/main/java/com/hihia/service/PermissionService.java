package com.hihia.service;

import com.hihia.domain.Permission;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 17:49
 * @email 18221221@bjtu.edu.cn
 */
public interface PermissionService {

    public List<Permission> findPermissionByRoleId(String id);
}
