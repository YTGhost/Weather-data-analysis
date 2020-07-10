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
}
