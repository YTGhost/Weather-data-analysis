package com.hihia.controller;

import com.hihia.domain.Dept;
import com.hihia.domain.User_Dept;
import com.hihia.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteDept(@PathVariable(name = "id") String id){
        deptService.deleteDept(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "删除成功");
        map.put("data", null);
        return map;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> modifyDept(@RequestBody Dept dept) {
        String id = dept.getId().toString();
        String deptName = dept.getDeptName();
        deptService.modifyDept(id, deptName);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "修改成功");
        map.put("data", null);
        return map;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> createDept(@RequestParam(name = "deptName") String deptName) {
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
            map.put("msg", "查询失败");
            map.put("data", null);
        }
        return map;
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findDeptById(@PathVariable(name = "id") String id){
        Dept dept = deptService.findDeptById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("data", dept);
        return map;
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> assignRole(@RequestBody User_Dept user_dept) {
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

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkDeptName(@RequestParam("deptName") String deptName){
        System.out.println(deptName);
        Dept dept = deptService.checkDeptName(deptName);
        Map<String, Object> map = new HashMap<>();
        if (dept == null) {
            map.put("code", 1);
            map.put("msg", "该部门不存在");
            map.put("data", null);
        } else {
            map.put("code", 0);
            map.put("msg", "该部门已存在");
            map.put("data", null);
        }
        return map;
    }
}
