package com.hihia.dao;

import com.hihia.domain.Menu;
import com.hihia.domain.Role_Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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

    @Select("select * from menus")
    public List<Menu> findAll();

    @Select("select * from roles_menus where roleId=#{roleId} and menuId=#{menuId}")
    public Role_Menu checkMenu(@Param("roleId") String roleId, @Param("menuId") String menuId);

    @Insert("insert into roles_menus (roleId, menuId) value(#{roleId}, #{menuId})")
    public void assignPower(@Param("roleId") String roleId, @Param("menuId") String menuId);

    @Delete("delete from roles_menus where roleId=#{roleId} and menuId=#{menuId}")
    public void deletePower(@Param("roleId") String roleId, @Param("menuId") String menuId);
}
