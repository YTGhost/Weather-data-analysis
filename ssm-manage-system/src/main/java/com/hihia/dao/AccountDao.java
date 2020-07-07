package com.hihia.dao;

/**
 * @author 邓梁
 * @date 2020/7/7 10:27
 * @email 18221221@bjtu.edu.cn
 */

import com.hihia.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户dao接口
 */
@Repository
public interface AccountDao {

    /**
     * 查询所有账户
     * @return
     */
    @Select("select * from account")
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    @Insert("insert into account (name, money) values (#{name}, #{money})")
    public void saveAccount(Account account);
}
