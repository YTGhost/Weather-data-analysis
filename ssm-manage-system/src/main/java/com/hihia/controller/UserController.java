package com.hihia.controller;

/**
 * @author 邓梁
 * @date 2020/7/9 0:19
 * @email 18221221@bjtu.edu.cn
 */

import com.hihia.domain.UserInfo;
import com.hihia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询指定用户名用户，GET请求
     * @param username
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findByUsername(@RequestParam(name="username") String username){
        UserInfo user = userService.findByUsername(username);
        Map<String, Object> map = new HashMap<>();
        if(user != null){
            map.put("code", 1);
            map.put("msg", "查询成功");
            map.put("data", user);
        }else{
            map.put("code", 0);
            map.put("msg", "没有查询到当前用户");
            map.put("data", null);
        }
        return map;
    }
}
