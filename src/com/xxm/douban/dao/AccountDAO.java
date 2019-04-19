package com.xxm.douban.dao;

import com.xxm.douban.entity.Account;

public interface AccountDAO {
	void addAccount(Account account);//注册
	void login(Account account);//登录
}
