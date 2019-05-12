package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;

public interface ChatDAO {
	
	Msg getChatFriend(String user_email);//获取密友列表
	
	Msg sendChat(String from_email, String to_email, String msg);//发信息
	
	Msg readChat(String from_email, String to_email);//读信息
	
	Msg deleteChat(String id);//删信息

}
