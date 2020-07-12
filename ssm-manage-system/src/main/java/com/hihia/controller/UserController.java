package com.hihia.controller;

/**
 * @author 邓梁
 * @date 2020/7/9 0:19
 * @email 18221221@bjtu.edu.cn
 */

import com.hihia.dao.UserDao;
import com.hihia.domain.Role;
import com.hihia.domain.UserInfo;
import com.hihia.domain.User_Role;
import com.hihia.service.RoleService;
import com.hihia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> modifyUserInfo(@RequestBody UserInfo userInfo){
        String id = userInfo.getId().toString();
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        String email = userInfo.getEmail();
        userService.modifyUserInfo(id, username, password, email);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "修改成功");
        map.put("data", null);
        return map;
    }

    /**
     * 用户管理中获取当前用户角色所应获取到的用户信息
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAll(User_Role user_role){
        String userId = user_role.getUserId();
        String roleId = user_role.getRoleId();
        List<UserInfo> userInfoList;
        if("1".equals(roleId)){
            userInfoList = userService.getUserInfoByRoot();
        }else{
            userInfoList = userService.getUserInfoByAdmin(userId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("data", userInfoList);
        return map;
    }


    /**
     * 查询指定用户名用户，GET请求
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findByUsername(@RequestParam(name = "username") String username) {
        UserInfo user = userService.findByUsername(username);
        Map<String, Object> map = new HashMap<>();
        if (user != null) {
            map.put("code", 1);
            map.put("msg", "查询成功");
            map.put("data", user);
        } else {
            map.put("code", 0);
            map.put("msg", "没有查询到当前用户");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 创建普通用户
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createUser(@RequestBody UserInfo userInfo){
        userService.createUser(userInfo);
        String id = userService.findIdByUsername(userInfo.getUsername());
        // 创建用户时都默认为普通用户
        roleService.assignRole(id, "2");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "创建成功");
        map.put("data", null);
        return map;
    }

    /**
     * 删除指定id的用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteById(@PathVariable(name = "id") String id){
        Map<String, Object> map = new HashMap<>();
        if("1".equals(id)){
            map.put("code", 0);
            map.put("msg", "删除失败");
            map.put("data", null);
        }else{
            userService.deleteById(id);
            map.put("code", 1);
            map.put("msg", "删除成功");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkUsername(@RequestParam(name = "username") String username) {
        UserInfo user = userService.checkUsername(username);
        Map<String, Object> map = new HashMap<>();
        if (user != null) {
            map.put("code", 0);
            map.put("msg", "该用户名已存在");
            map.put("data", null);
        } else {
            map.put("code", 1);
            map.put("msg", "该用户名不存在");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 查询邮箱是否存在
     * @param email
     * @return
     */
    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam(name = "email") String email) {
        UserInfo user = userService.checkEmail(email);
        Map<String, Object> map = new HashMap<>();
        if (user != null) {
            map.put("code", 0);
            map.put("msg", "该邮箱已存在");
            map.put("data", null);
        } else {
            map.put("code", 1);
            map.put("msg", "该邮箱不存在");
            map.put("data", null);
        }
        return map;
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findById(@PathVariable(name = "id") String id){
        UserInfo userInfo = userService.findById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("data", userInfo);
        return map;
    }
}
