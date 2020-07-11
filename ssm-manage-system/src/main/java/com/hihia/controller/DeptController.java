package com.hihia.controller;

import com.hihia.domain.Dept;
import com.hihia.domain.User_Dept;
import com.hihia.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 邓梁
 * @date 2020/7/9 15:24
 * @email 18221221@bjtu.edu.cn
 */

@Controller
@CrossOrigin
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/delete")
//    public Map<String, Object> deleteDept(String deptId){
//
//    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> modifyDept(Dept dept) {
        String id = dept.getId().toString();
        String deptName = dept.getDeptName();
        deptService.modifyDept(id, deptName);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "修改成功");
        map.put("data", null);
        return map;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createDept(String deptName) {
        Dept dept = deptService.checkDeptName(deptName);
        Map<String, Object> map = new HashMap<>();
        if (dept == null) {
            deptService.createDept(deptName);
            map.put("code", 1);
            map.put("msg", "创建成功");
            map.put("data", null);
        } else {
            map.put("code", 0);
            map.put("msg", "该部门已存在");
            map.put("data", null);
        }
        return map;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAll() {
        List<Dept> depts = deptService.findAll();
        Map<String, Object> map = new HashMap<>();
        if (depts != null) {
            map.put("code", 1);
            map.put("msg", "查询成功");
            map.put("data", depts);
        } else {
            map.put("code", 0);
            map.put("msg", "没有查询到角色列表");
            map.put("data", null);
        }
        return map;
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> assignRole(User_Dept user_dept) {
        String userId = user_dept.getUserId();
        String deptId = user_dept.getDeptId();
        User_Dept sign = deptService.checkDept(userId, deptId);
        if (sign == null) {
            deptService.assignDept(userId, deptId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "分配成功");
        map.put("data", null);
        return map;
    }
}
