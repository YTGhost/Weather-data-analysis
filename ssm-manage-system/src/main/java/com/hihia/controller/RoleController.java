package com.hihia.controller;

/**
 * @author 邓梁
 * @date 2020/7/9 15:24
 * @email 18221221@bjtu.edu.cn
 */

import com.hihia.domain.Role;
import com.hihia.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 角色Controller
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public List<Role> findRoleByUserId(@PathVariable(name="id") String id){
        return roleService.findRoleByUserId(id);
    }

}
