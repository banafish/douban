package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;

public interface FriendDAO {
	Msg setFollow(String user_email, String follow_email);//设置关注
	
	Msg getFollow(String currentPage, String user_email);//获得关注的人
	
	Msg cancelFollow(String user_email, String follow_email);//取消关注

}
