package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Friend;

public interface FriendDAO {
	Msg setFollow(String user_email, String follow_email);//设置关注
	
	Msg getFollow(String currentPage, String user_email);//获得关注的人
	
	Msg cancelFollow(String user_email, String follow_email);//取消关注
	
	Msg insertFriend(Friend friend);//跟好友有关的操作
	
	Msg getGroup(String host_email);//取得分组

}
