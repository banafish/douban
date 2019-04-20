package com.xxm.douban.service;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.AccountDAO;
import com.xxm.douban.entity.Account;

public class UserService {
  
	private AccountDAO accountDAO;

	public UserService(AccountDAO userDAO) {
		this.accountDAO = userDAO;
	}

	// 添加用户
	public Msg register(Account account) {
		return accountDAO.register(account);
	}

	// 登录
	public Msg login(Account account) {
		return accountDAO.login(account);
	}

}
