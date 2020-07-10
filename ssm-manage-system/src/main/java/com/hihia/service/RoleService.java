package com.hihia.service;

import com.hihia.domain.Role;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:25
 * @email 18221221@bjtu.edu.cn
 */
public interface RoleService {

    /**
     * 查询指定用户id的角色
     * @param id
     * @return
     */
    public List<Role> findRoleByUserId(String id);
}
