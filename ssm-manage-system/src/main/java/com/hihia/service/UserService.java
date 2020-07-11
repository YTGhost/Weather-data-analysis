package com.hihia.service;

import com.hihia.domain.UserInfo;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 0:10
 * @email 18221221@bjtu.edu.cn
 */
public interface UserService {

    /**
     * 查询指定用户名的用户信息
     * @param username
     * @return
     */
    public UserInfo findByUsername(String username);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    public UserInfo checkUsername(String username);

    /**
     * 查询邮箱是否存在
     * @param email
     * @return
     */
    public UserInfo checkEmail(String email);

    /**
     * 创建普通用户
     * @param userInfo
     */
    public void createUser(UserInfo userInfo);

    /**
     * 通过用户名查找其id
     * @param username
     * @return
     */
    public String findIdByUsername(String username);

    /**
     * 通过用户id来删除用户
     * @param id
     */
    public void deleteById(String id);

    /**
     * 系统管理员获取所有用户信息
     * @return
     */
    public List<UserInfo> getUserInfoByRoot();

    /**
     * 普通管理员获取用户信息
     * @return
     */
    public List<UserInfo> getUserInfoByAdmin(String userId);

    /**
     * 修改用户基本信息
     * @param username
     * @param password
     * @param email
     */
    public void modifyUserInfo(String id, String username, String password, String email);
}
