package com.xxm.douban.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Friend;
import com.xxm.douban.service.FriendService;
import com.xxm.douban.util.DateUtil;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/friendServlet")
public class FriendServlet extends HttpServlet {
	private FriendService friendService;
	
	private Friend friend;
	
	private String guest_email;
	
	private Account account;
	
	private String msg;//信息
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		friendService = (FriendService) getServletContext().getAttribute("friendService");
		
		// 获取用户属性
		account = (Account) session.getAttribute("account");
		//获取副邮箱
		guest_email = request.getParameter("guest_email");
		//获取信息
		msg = request.getParameter("msg");
		//获取方法
		String method = request.getParameter("method");
		if (method == null) {
			method = "";
		}
 		
		//申请好友
		if (method.equals("applyFriend")) {
			applyFriend(request, response);
			return;
		}
		
	}

	
	//申请好友
	private void applyFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		friend = new Friend();
		friend.setHost_email(account.getEmail());
		friend.setGuest_email(guest_email);
		friend.setMsg(msg);
		friend.setStatue("1");
		friend.setTable("t_friend");
		friend.setTime(DateUtil.currentTime());
		
		Msg result = friendService.insertFriend(friend);
		if (result.getResult().equals("操作成功")) {			
			response.getWriter().write("申请成功，请等待好友通过");
		} else {
			response.getWriter().write("申请失败");
		}
	}


}
