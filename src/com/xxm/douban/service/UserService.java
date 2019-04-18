package com.xxm.douban.service;

import com.xxm.douban.dao.AccountDAO;
import com.xxm.douban.entity.Account;

public class UserService {
	private AccountDAO accountDAO;
	
	public UserService(AccountDAO userDAO) {
		this.accountDAO = userDAO;
	}
	
	//判断用户是否存在
	public boolean isUserExisted(Account account) {
		return accountDAO.isUserExisted(account);
	}
	
	//添加用户
	public void add(Account account) {
		accountDAO.addAccount(account);
	}
	
}
