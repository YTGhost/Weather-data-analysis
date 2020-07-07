package com.hihia.service;

import com.hihia.domain.Account;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/7 10:30
 * @email 18221221@bjtu.edu.cn
 */
public interface AccountService {

    /**
     * 查询所有账户
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    public void saveAccount(Account account);
}
