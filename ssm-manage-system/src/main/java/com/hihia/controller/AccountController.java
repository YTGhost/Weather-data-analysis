package com.hihia.controller;

/**
 * @author 邓梁
 * @date 2020/7/7 10:32
 * @email 18221221@bjtu.edu.cn
 */

import com.hihia.domain.Account;
import com.hihia.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户web
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String, Object> findAll(){
        System.out.println("表现层：查询所有账户...");
        // 调用service的方法
        List<Account> list = accountService.findAll();
        for(Account account : list){
            System.out.println(account);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("data", list);
        return map;
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping("/save")
    public void save(@RequestBody Account account){
        System.out.println("业务层：保存账号...");
        accountService.saveAccount(account);
    }
}
