package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;

public interface AccountDAO {
	Msg register(Account account);//注册
	
	Msg login(Account account);//登录
	
	Msg resetPassword(Account account);//重置密码
	
	Msg isExistAccount(String email);//判断是否存在该用户
	
}
