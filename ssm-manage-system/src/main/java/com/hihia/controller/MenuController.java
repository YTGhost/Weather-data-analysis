package com.hihia.controller;

import com.hihia.domain.Menu;
import com.hihia.service.MenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/12 14:48
 * @email 18221221@bjtu.edu.cn
 */

@Controller
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> findAll(){
        return menuService.findAll();
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    @ResponseBody
    public void assignMenuPower(@RequestParam(name = "ids") int[] ids, @RequestParam(name = "roleId") String roleId){
        int[] sign = new int[4];
        for(int i = 0; i < ids.length; i++)
        {
            sign[ids[i]] = 1;
        }
        for(int i = 1; i < 4; i++)
        {
            // 等于1说明有这个菜单
            if(sign[i] == 1){
                menuService.assignPower(roleId, String.valueOf(i));
            }else{
                menuService.deletePower(roleId, String.valueOf(i));
            }
        }
    }
}
