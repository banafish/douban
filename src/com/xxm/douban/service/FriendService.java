package com.xxm.douban.service;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.FriendDAO;
import com.xxm.douban.entity.Friend;

public class FriendService {
	private FriendDAO friendDAO;

	public FriendService(FriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}

	// 设置关注
	public Msg setFollow(String user_email, String follow_email) {
		return friendDAO.setFollow(user_email, follow_email);
	}

	// 设置关注
	public Msg cancelFollow(String user_email, String follow_email) {
		return friendDAO.cancelFollow(user_email, follow_email);
	}

	// 获得关注的人
	public Msg getFollow(String currentPage, String user_email) {
		return friendDAO.getFollow(currentPage, user_email);
	}

	// 跟好友有关的操作
	public Msg insertFriend(Friend friend) {
		return friendDAO.insertFriend(friend);
	}
	
	//取得分组
	public Msg getGroup(String host_email) {
		return friendDAO.getGroup(host_email);
	}

}
