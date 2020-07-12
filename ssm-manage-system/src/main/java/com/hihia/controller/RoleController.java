package com.hihia.controller;

/**
 * @author 邓梁
 * @date 2020/7/9 15:24
 * @email 18221221@bjtu.edu.cn
 */

import com.hihia.domain.Role;
import com.hihia.domain.User_Role;
import com.hihia.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色Controller
 */
@Controller
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createRole(String roleName) {
        Role role = roleService.checkRoleName(roleName);
        Map<String, Object> map = new HashMap<>();
        if(role == null){
            roleService.createRole(roleName);
            map.put("code", 1);
            map.put("msg", "创建成功");
            map.put("data", null);
        }else {
            map.put("code", 0);
            map.put("msg", "该角色已存在");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 根据用户id获取其角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public List<Role> findRoleByUserId(@PathVariable(name = "id") String id) {
        return roleService.findRoleByUserId(id);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAll() {
        List<Role> roles = roleService.findAll();
        Map<String, Object> map = new HashMap<>();
        if (roles != null) {
            map.put("code", 1);
            map.put("msg", "查询成功");
            map.put("data", roles);
        } else {
            map.put("code", 0);
            map.put("msg", "没有查询到角色列表");
            map.put("data", null);
        }
        return map;
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> assignRole(User_Role user_role) {
        String userId = user_role.getUserId();
        String roleId = user_role.getRoleId();
        User_Role sign = roleService.checkRole(userId);
        if (sign == null) {
            roleService.assignRole(userId, roleId);
        } else {
            roleService.changeRole(userId, roleId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "分配成功");
        map.put("data", null);
        return map;
    }
}
