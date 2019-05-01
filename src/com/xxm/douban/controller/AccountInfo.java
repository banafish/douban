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
import com.xxm.douban.service.UserService;

/**
 * Servlet implementation class AccountInfo
 */
@WebServlet("/accountInfo")
public class AccountInfo extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");

		// 获取用户属性
		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			response.getWriter().write("请先登录");
			return;
		}
		
		//获取信息
		String pic = request.getParameter("picture");
		String sign = request.getParameter("sign");
		
		Msg msg = null;
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		
		//添加头像
		if (pic != null) {
			msg = userService.addAvatar(pic, account.getEmail());
			//把头像路径放进account
			account.setAvatar((String)msg.getMessage());
			response.getWriter().write(msg.getResult());
		}
		//添加签名
		if (sign != null) {
			msg = userService.addSign(sign, account.getEmail());
			//把签名放进account
			account.setSign((String)msg.getMessage());
			response.getWriter().write(msg.getResult());
		}
		
	}

}
