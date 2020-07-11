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
}
