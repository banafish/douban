package com.xxm.douban.dao;

import com.xxm.douban.entity.Account;

public interface AccountDAO {
	boolean isUserExisted(Account account);
	void addAccount(Account account);
	Account getAccount(Account account);
}
