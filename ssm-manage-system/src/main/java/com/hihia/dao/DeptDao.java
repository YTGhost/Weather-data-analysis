package com.hihia.dao;

import com.hihia.domain.Dept;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:29
 * @email 18221221@bjtu.edu.cn
 */

@Repository
public interface DeptDao {

    @Select("select * from depts where id in (select deptId from users_depts where userId=#{userId})")
    public List<Dept> findDeptByUserId(String userId);
}
