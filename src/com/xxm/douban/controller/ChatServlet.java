package com.xxm.douban.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.service.ChatService;
import com.xxm.douban.service.FriendService;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chatServlet")
public class ChatServlet extends HttpServlet {
	
	private ChatService chatService;
	
	private Msg msg;
	
	private Account account;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		chatService = (ChatService) getServletContext().getAttribute("chatService");
		response.setCharacterEncoding("utf-8");
		
		// 获取用户属性
		account = (Account) session.getAttribute("account");
		
		msg = chatService.readChat(request.getParameter("from_email"), account.getEmail());
		Map map = (Map) msg.getMessage();
		
		if (map == null) {
			response.getWriter().write("");
			return;
		}
		//map不为空
		String content = (String) map.get("msg");
		String id = (String) map.get("id");
		if (msg.getResult().equals("读信息成功")) {
			chatService.deleteChat(id);//删除信息
			response.getWriter().write(content);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		chatService = (ChatService) getServletContext().getAttribute("chatService");
		response.setCharacterEncoding("utf-8");
		
		// 获取用户属性
		account = (Account) session.getAttribute("account");
	
		msg = chatService.sendChat(account.getEmail(), request.getParameter("to_email"), request.getParameter("msg"));
	
		if (msg.getResult().equals("发送成功")) {
			response.getWriter().write("发送成功");
		}
	}

}
