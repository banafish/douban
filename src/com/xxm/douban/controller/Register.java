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

/**
 * Servlet implementation class Register
 */
@WebServlet(urlPatterns = { "/register" }, initParams = { @WebInitParam(name = "SUCCESS_VIEW", value = "index.html"),
		@WebInitParam(name = "ERROR_VIEW", value = "register.html") })
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
		
		Account account = new Account();
		account.setEmail(request.getParameter("email"));
		account.setName(request.getParameter("nick"));
		account.setPassword(request.getParameter("password"));
	
		// 把account属性加到session
		session.setAttribute("account", account);

		UserService userService = (UserService) getServletContext().getAttribute("userService");
		Msg msg = userService.register(account);
		System.out.println(msg.getResult());
		request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
	}
}
