package com.xxm.douban.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.service.UserService;
import com.xxm.douban.util.EncrypMD5Util;

/**
 * Servlet implementation class Register
 */
@WebServlet(urlPatterns = { "/register" }, initParams = { @WebInitParam(name = "SUCCESS_VIEW", value = "getArticleByPage?p=1"),
		@WebInitParam(name = "ERROR_VIEW", value = "register.jsp") })
public class Register extends HttpServlet {
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;

	@Override
	public void init() throws ServletException {
		SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		request.setCharacterEncoding("UTF-8");
		
		Account account = new Account();
		account.setEmail(request.getParameter("email"));
		account.setName(request.getParameter("nick"));
		account.setPassword(EncrypMD5Util.getMD5String(request.getParameter("password")));
		account.setAvatar("/file/user_normal.jpg");//默认头像
		account.setSign("添加签名文档");//默认签名

		UserService userService = (UserService) getServletContext().getAttribute("userService");
		Msg msg = userService.register(account);
		if (msg.getResult().equals("注册成功")) {
			// 把account属性加到session
			session.setAttribute("account", account);
			response.sendRedirect(SUCCESS_VIEW);
		} else {
			response.sendRedirect(ERROR_VIEW);
		}
		
	}
}
