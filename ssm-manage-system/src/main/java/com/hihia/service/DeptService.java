package com.hihia.service;

import com.hihia.domain.Dept;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:25
 * @email 18221221@bjtu.edu.cn
 */
public interface DeptService {

    public List<Dept> findDeptByUserId(String id);
}
