package com.hihia.service.impl;

import com.hihia.dao.MenusDao;
import com.hihia.domain.Menu;
import com.hihia.domain.Role_Menu;
import com.hihia.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 18:47
 * @email 18221221@bjtu.edu.cn
 */

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenusDao menusDao;

    @Override
    public List<Menu> findMenuByRoleId(String id) {
        return menusDao.findMenuByRoleId(id);
    }

    @Override
    public List<Menu> findAll() {
        return menusDao.findAll();
    }

    @Override
    public void assignPower(String roleId, String menuId) {
        Role_Menu role_menu = menusDao.checkMenu(roleId, menuId);
        if(role_menu == null){
            menusDao.assignPower(roleId, menuId);
        }
    }

    @Override
    public void deletePower(String roleId, String menuId) {
        Role_Menu role_menu = menusDao.checkMenu(roleId, menuId);
        if(role_menu != null){
            menusDao.deletePower(roleId, menuId);
        }
    }
}
