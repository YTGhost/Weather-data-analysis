package com.hihia.controller;

import com.hihia.domain.Permission;
import com.hihia.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/12 14:53
 * @email 18221221@bjtu.edu.cn
 */

@Controller
@CrossOrigin
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Permission> findAll(){
        return permissionService.findAll();
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    @ResponseBody
    public void assignMenuPower(@RequestParam(name = "ids") int[] ids, @RequestParam(name = "roleId") String roleId){
        int[] sign = new int[13];
        for(int i = 0; i < ids.length; i++)
        {
            sign[ids[i]] = 1;
        }
        for(int i = 1; i < 13; i++)
        {
            // 等于1说明有这个菜单
            if(sign[i] == 1){
                permissionService.assignPower(roleId, String.valueOf(i));
            }else{
                permissionService.deletePower(roleId, String.valueOf(i));
            }
        }
    }
}
