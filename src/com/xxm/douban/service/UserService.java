package com.xxm.douban.service;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.AccountDAO;
import com.xxm.douban.dao.UploadPicDAO;
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

	// 重置密码
	public Msg resetPassword(Account account) {
		return accountDAO.resetPassword(account);
	}

	// 判断用户是否存在
	public Msg isExistAccount(String email) {
		return accountDAO.isExistAccount(email);
	}

	// 添加头像
	public Msg addAvatar(String pic, String email) {
		UploadPicDAO uploadPaicDAO = new UploadPicDAO();
		return accountDAO.addAvatar(uploadPaicDAO.uploadPic(pic, email).split(",")[0], email);
	}

	// 添加签名
	public Msg addSign(String sign, String email) {
		return accountDAO.addSign(sign, email);
	}

}
