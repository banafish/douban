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
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = { "/login" }, initParams = { @WebInitParam(name = "SUCCESS_VIEW", value = "register.jsp"),
		@WebInitParam(name = "ERROR_VIEW", value = "index.jsp") })
public class Login extends HttpServlet {
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
		Msg msg = null;
		// 从session中获取真正的验证码
		String session_vcode = (String) session.getAttribute("verifyCode");
		// 获取用户输入的验证码
		String form_vcode = request.getParameter("num"); 
		// 进行判断
		if (!(session_vcode.equalsIgnoreCase(form_vcode))) 
		{
			msg = new Msg("验证码错误", null);
			// 如果错误就将错误信息发送给客户端
			request.setAttribute("msg", msg); 
			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
			return;
		}

		Account account = new Account();
		account.setEmail(request.getParameter("email"));
		account.setPassword(EncrypMD5Util.getMD5String(request.getParameter("password")));
		
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		msg = userService.login(account);
		if (msg.getResult().equals("登录成功")) {
			//把account属性加到session
			session.setAttribute("account", msg.getMessage());
			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
		} else {
			request.setAttribute("msg", msg); 
			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
		}
	}
	
}
