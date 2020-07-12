package com.hihia.service.impl;

import com.hihia.dao.DeptDao;
import com.hihia.dao.UserDao;
import com.hihia.domain.Dept;
import com.hihia.domain.UserInfo;
import com.hihia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/9 0:12
 * @email 18221221@bjtu.edu.cn
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DeptDao deptDao;

    @Override
    public UserInfo findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserInfo checkUsername(String username) {
        return userDao.checkUsername(username);
    }

    @Override
    public UserInfo checkEmail(String email) {
        return userDao.checkEmail(email);
    }

    @Override
    public void createUser(UserInfo userInfo) {
        userDao.createUser(userInfo);
    }

    @Override
    public String findIdByUsername(String username) {
        return userDao.findIdByUsername(username);
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteDeptLinkById(id);
        userDao.deleteRoleLinkById(id);
        userDao.deleteInfoById(id);
    }

    @Override
    public List<UserInfo> getUserInfoByRoot() {
        return userDao.getUserInfoByRoot();
    }

    @Override
    public List<UserInfo> getUserInfoByAdmin(String userId) {
        List<Dept> depts = deptDao.findDeptByUserId(userId);
        List<UserInfo> userInfoList = null;
        for(int i = 0; i < depts.size(); i++)
        {
            if(i == 0){
                userInfoList = deptDao.findUserInfoByDeptId(depts.get(i).getId().toString());
            }else{
                userInfoList.addAll(deptDao.findUserInfoByDeptId(depts.get(i).getId().toString()));
            }
        }
        return userInfoList;
    }

    @Override
    public void modifyUserInfo(String id, String username, String password, String email) {
        userDao.modifyUserInfo(id, username, password, email);
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }
}
