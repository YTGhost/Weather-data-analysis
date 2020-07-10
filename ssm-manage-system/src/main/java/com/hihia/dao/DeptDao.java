package com.hihia.dao;

import com.hihia.domain.Dept;
import com.hihia.domain.User_Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 15:29
 * @email 18221221@bjtu.edu.cn
 */

@Repository
public interface DeptDao {

    /**
     * 通过用户id来查找其所处的所有部门
     * @param userId
     * @return
     */
    @Select("select * from depts where id in (select deptId from users_depts where userId=#{userId})")
    public List<Dept> findDeptByUserId(String userId);

    /**
     * 获取部门列表
     * @return
     */
    @Select("select * from depts")
    public List<Dept> findAll();

    /**
     * 检查该用户是否已经属于当前部门
     * @param userId
     * @param deptId
     * @return
     */
    @Select("select * from users_depts where userId=#{userId} and deptId=#{deptId}")
    @Results({
            @Result(property = "userId", column = "userId"),
            @Result(property = "deptId", column = "deptId")
    })
    public User_Dept checkDept(@Param("userId") String userId, @Param("deptId") String deptId);

    /**
     * 给用户分配部门
     * @param userId
     * @param deptId
     */
    @Insert("insert into users_depts (userId, deptId) values (#{userId}, #{deptId})")
    public void assignDept(@Param("userId") String userId, @Param("deptId") String deptId);
}
