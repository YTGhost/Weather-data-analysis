package com.hihia.dao;

import com.hihia.domain.Menu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 18:38
 * @email 18221221@bjtu.edu.cn
 */

@Repository
public interface MenusDao {

    /**
     * 根据roleId查询当前角色能访问的侧边栏
     * @param id
     * @return
     */
    @Select("select * from menus where id in (select menuId from roles_menus where roleId=#{id})")
    public List<Menu> findMenuByRoleId(String id);
}
