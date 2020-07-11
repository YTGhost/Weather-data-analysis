package com.hihia.service.impl;

import com.hihia.dao.DeptDao;
import com.hihia.domain.Dept;
import com.hihia.domain.User_Dept;
import com.hihia.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:36
 * @email 18221221@bjtu.edu.cn
 */

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> findDeptByUserId(String id) {
        return deptDao.findDeptByUserId(id);
    }

    @Override
    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    @Override
    public User_Dept checkDept(String userId, String deptId) {
        return deptDao.checkDept(userId, deptId);
    }

    @Override
    public void assignDept(String userId, String deptId) {
        deptDao.assignDept(userId, deptId);
    }

    @Override
    public void createDept(String deptName) {
        deptDao.createDept(deptName);
    }

    @Override
    public Dept checkDeptName(String deptName) {
        return deptDao.checkDeptName(deptName);
    }

    @Override
    public void modifyDept(String id, String deptName) {
        deptDao.modifyDept(id, deptName);
    }

    @Override
    public void deleteDept(String id) {
        deptDao.deleteDeptInfo(id);
        deptDao.deleteDeptAsso(id);
    }

    @Override
    public Dept findDeptById(String id) {
        return deptDao.findDeptById(id);
    }
}
