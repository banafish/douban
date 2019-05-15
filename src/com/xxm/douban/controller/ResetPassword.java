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
 * Servlet implementation class ResetPassword
 * 重置密码
 */
@WebServlet(urlPatterns = { "/resetPassword" }, initParams = {
		@WebInitParam(name = "SUCCESS_VIEW", value = "index.jsp"),
		@WebInitParam(name = "ERROR_VIEW", value = "register.jsp") })
public class ResetPassword extends HttpServlet {
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;

	@Override
	public void init() throws ServletException {
		SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		Account account = new Account();
		account.setEmail((String) request.getSession().getAttribute("email"));
		account.setPassword(EncrypMD5Util.getMD5String(request.getParameter("password")));
		
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		Msg msg = userService.resetPassword(account);
		if (msg.getResult().equals("重置密码成功")) {
			response.sendRedirect(SUCCESS_VIEW);
		} else {
			response.sendRedirect(ERROR_VIEW);
		}
	}

}
