package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Friend;

public interface FriendDAO {
	Msg setFollow(String user_email, String follow_email);//设置关注
	
	Msg getFollow(String currentPage, String user_email);//获得关注的人
	
	Msg getFollowCount(String email);//获得关注的人总数
	
	Msg cancelFollow(String user_email, String follow_email);//取消关注
	
	Msg insertFriend(Friend friend);//跟好友有关的操作
	
	Msg getGroup(String host_email);//取得分组
	
	Msg getFriend(String currentPage, String limit);//取得朋友申请，朋友列表
	
	Msg getFriendCount(String email, String statue, String host_black, String guest_black);// 获得朋友，好友申请，黑名单总数
	
	Msg deleteFriend(String host_email, String guest_email);//删除好友
	
	Msg getFriendByGroup(String currentPage, String host_email, String group);//通过分类获取好友列表
	
	Msg getFriendGroupCount(String host_email, String group);//分类获取好友列表的好友总数
	
	Msg setBlack(Friend friend);//设置黑名单
	
	Msg inBlack(String host_email, String guest_email);//判断在不在黑名单
	
	Msg getDouYou(String currentPage, String email);//获取豆邮
	
	Msg getDouYouCount(String email);//获取豆邮总数
	
	Msg report(String email, String name);//举报
	
	Msg getReport(String currentPage);//获取举报列表
	
	Msg getReportCount();//获得举报总数
	
	Msg deleteReport(String email);//删除举报
	
	Msg setReport(String email, String end_time);//封号
	
	Msg isReport(String email);//判断是否被封号
	
	Msg searchPeople(String currentPage, String keyWord);//搜人
	
	Msg searchPeopleCount(String keyWord);//搜人的总数

}
