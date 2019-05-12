package com.xxm.douban.service;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.ArticleDAO;
import com.xxm.douban.dao.ChatDAO;

public class ChatService {
	private ChatDAO chatDAO;

	public ChatService(ChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}
	
	//获取密友列表
	public Msg getChatFriend(String user_email) {
		return chatDAO.getChatFriend(user_email);
	}
	
	//发信息
	public Msg sendChat(String from_email, String to_email, String msg) {
		return chatDAO.sendChat(from_email, to_email, msg);
	}
	
	//读信息
	public Msg readChat(String from_email, String to_email) {
		return chatDAO.readChat(from_email, to_email);
	}
	
	//删信息
	public Msg deleteChat(String id) {
		return chatDAO.deleteChat(id);
	}

}
