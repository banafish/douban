package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;

public interface FriendDAO {
	Msg setFollow(String user_email, String follow_email);//设置关注

}
