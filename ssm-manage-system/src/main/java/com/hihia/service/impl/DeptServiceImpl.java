package com.hihia.service.impl;

import com.hihia.dao.DeptDao;
import com.hihia.domain.Dept;
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
}
