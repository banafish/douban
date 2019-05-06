package com.xxm.douban.service;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.FriendDAO;

public class FriendService {
	private FriendDAO friendDAO;

	public FriendService(FriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}
	
	//设置关注 
	public Msg setFollow(String user_email, String follow_email) {
		return friendDAO.setFollow(user_email, follow_email);
	}

}
