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

	// 取消关注
	public Msg cancelFollow(String user_email, String follow_email) {
		return friendDAO.cancelFollow(user_email, follow_email);
	}

	// 获得关注的人
	public Msg getFollow(String currentPage, String user_email) {
		return friendDAO.getFollow(currentPage, user_email);
	}

	// 获得关注的人的总数
	public Msg getFollowCount(String user_email) {
		return friendDAO.getFollowCount(user_email);
	}

	// 跟好友有关的操作
	public Msg insertFriend(Friend friend) {
		if (friend.getMsg() == null || friend.getMsg().equals("")) {
			friend.setMsg("好友");// 默认设为好友
		}
		return friendDAO.insertFriend(friend);
	}

	// 取得分组
	public Msg getGroup(String host_email) {
		return friendDAO.getGroup(host_email);
	}

	// 取得朋友列表
	public Msg getFriendList(String currentPage, String user_email) {
		String limit = " t_friend, t_account WHERE t_friend.host_email = '" + user_email
				+ "' AND t_friend.guest_email = t_account.email "
				+ "AND t_friend.statue = 0 and t_friend.host_black = 0 and t_friend.guest_black = 0"
				+ " ORDER BY t_friend.time DESC";
		return friendDAO.getFriend(currentPage, limit);
	}

	// 获得好友总数
	public Msg getFriendCount(String email) {
		return friendDAO.getFriendCount(email, "0", "0", "0");
	}

	// 获得好友申请总数
	public Msg getApplyCount(String email) {
		return friendDAO.getFriendCount(email, "0", "0", "0");
	}

	// 获得好友申请总数
	public Msg getBlackCount(String email) {
		return friendDAO.getFriendCount(email, "0", "1", "0");
	}

	// 通过分类取得朋友列表
	public Msg getFriendByGroup(String currentPage, String user_email, String group) {
		return friendDAO.getFriendByGroup(currentPage, user_email, group);
	}

	// 分类获取好友列表的好友总数
	public Msg getFriendGroupCount(String host_email, String group) {
		return friendDAO.getFriendGroupCount(host_email, group);
	}

	// 取得好友申请
	public Msg getApply(String currentPage, String user_email) {
		String limit = " t_friend, t_account WHERE t_friend.guest_email = '" + user_email
				+ "' AND t_friend.host_email = t_account.email " + "AND t_friend.statue = 1 "
				+ " ORDER BY t_friend.time DESC";
		return friendDAO.getFriend(currentPage, limit);
	}

	// 删除好友
	public Msg deleteFriend(String host_email, String guest_email) {
		return friendDAO.deleteFriend(host_email, guest_email);
	}

	// 设置黑名单
	public Msg setBlack(Friend friend) {
		return friendDAO.setBlack(friend);
	}

	// 判断在不在黑名单
	public Msg inBlack(String host_email, String guest_email) {
		return friendDAO.inBlack(host_email, guest_email);
	}

	// 查看黑名单
	public Msg getBlack(String currentPage, String user_email) {
		String limit = " t_friend, t_account WHERE t_friend.host_email = '" + user_email
				+ "' AND t_friend.guest_email = t_account.email "
				+ "AND t_friend.statue = 0 and t_friend.host_black = 1" + " ORDER BY t_friend.time DESC";
		return friendDAO.getFriend(currentPage, limit);
	}
	
	//获取豆邮
	public Msg getDouYou(String currentPage, String email) {
		return friendDAO.getDouYou(currentPage, email);
	}
	
	// 获取豆邮总数
	public Msg getDouYouCount(String email) {
		return friendDAO.getDouYouCount(email);
	}

}
