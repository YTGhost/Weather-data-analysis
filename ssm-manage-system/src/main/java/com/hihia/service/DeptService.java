package com.hihia.service;

import com.hihia.domain.Dept;
import com.hihia.domain.User_Dept;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:25
 * @email 18221221@bjtu.edu.cn
 */
public interface DeptService {

    /**
     * 通过用户id查找其所在部门列表
     * @param id
     * @return
     */
    public List<Dept> findDeptByUserId(String id);

    /**
     * 获取部门列表
     * @return
     */
    public List<Dept> findAll();

    /**
     * 检查给用户分配的部门该用户是否已经属于
     * @param userId
     * @param deptId
     * @return
     */
    public User_Dept checkDept(String userId, String deptId);

    /**
     * 给用户分配部门
     * @param userId
     * @param deptId
     */
    public void assignDept(String userId, String deptId);

    /**
     * 创建部门
     * @param deptName
     */
    public void createDept(String deptName);

    /**
     * 检查部门名字是否已存在
     * @param deptName
     * @return
     */
    public Dept checkDeptName(String deptName);

    /**
     * 修改部门信息
     * @param id
     * @param deptName
     */
    public void modifyDept(String id, String deptName);

    /**
     * 通过部门id来删除部门
     * @param id
     */
    public void deleteDept(String id);

    /**
     * 根据部门id来查询部门信息
     * @param id
     * @return
     */
    public Dept findDeptById(String id);
}
