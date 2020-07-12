package com.hihia.service;

import com.hihia.domain.Menu;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 18:47
 * @email 18221221@bjtu.edu.cn
 */
public interface MenuService {

    public List<Menu> findMenuByRoleId(String id);

    public List<Menu> findAll();

    public void assignPower(String roleId, String menuId);

    public void deletePower(String roleId, String menuId);
}
