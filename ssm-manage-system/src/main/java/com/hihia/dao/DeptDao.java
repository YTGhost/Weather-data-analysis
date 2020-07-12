package com.hihia.dao;

import com.hihia.domain.Dept;
import com.hihia.domain.UserInfo;
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

    @Select("select * from users where id in (select userId from users_depts where deptId=#{deptId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.RoleDao.findRoleByUserId")),
            @Result(property = "depts", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hihia.dao.DeptDao.findDeptByUserId"))
    })
    public List<UserInfo> findUserInfoByDeptId(String deptId);

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

    /**
     * 创建新部门
     * @param deptName
     */
    @Insert("insert into depts (deptName) values (#{deptName})")
    public void createDept(String deptName);

    /**
     * 检查是否已经有该部门
     * @param deptName
     * @return
     */
    @Select("select * from depts where deptName=#{deptName}")
    public Dept checkDeptName(String deptName);

    /**
     * 修改部门基本信息
     * @param id
     * @param deptName
     */
    @Update("update depts set deptName=#{deptName} where id=#{id}")
    public void modifyDept(@Param("id") String id, @Param("deptName") String deptName);

    /**
     * 删除部门基本信息
     * @param id
     */
    @Delete("delete from depts where id=#{id}")
    public void deleteDeptInfo(String id);

    /**
     * 删除部门和用户之间的联系
     * @param id
     */
    @Delete("delete from users_depts where deptId=#{id}")
    public void deleteDeptAsso(String id);

    @Select("select * from depts where id=#{id}")
    public Dept findDeptById(String id);
}
