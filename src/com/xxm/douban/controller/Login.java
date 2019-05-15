package com.xxm.douban.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.service.FriendService;
import com.xxm.douban.service.UserService;
import com.xxm.douban.util.EncrypMD5Util;

/**
 * Servlet implementation class Login
 * 登录
 */
@WebServlet(urlPatterns = { "/login" }, initParams = { @WebInitParam(name = "SUCCESS_VIEW", value = "homePage?p=1"),
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
		FriendService friendService = (FriendService) getServletContext().getAttribute("friendService");
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		HttpSession session = request.getSession(); 
		Cookie cookie;		
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
		
		msg = userService.login(account);
		
		if (msg.getResult().equals("登录成功")) {
			
			//设置自动登录功能
			if (request.getParameter("item_remember") != null) {				
				//用户属性用,隔开
				String value = account.getEmail() + "," + account.getName() + "," + account.getAvatar();
				cookie = new Cookie("autoLogin", value);					
			} else {
				cookie = new Cookie("autoLogin", "off");
			}
			cookie.setMaxAge(7*24*60*60);//一周内有效
			response.addCookie(cookie);
			
			//看是否被封号
			Msg report = friendService.isReport(account.getEmail());
			if (report.getResult().equals("被封号")) {
				request.setAttribute("msg", new Msg("你的账户被封到：" + report.getMessage(), null)); 
				request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
				return;
			}
			
			//获取用户角色
			Msg msgRole = userService.getRole(account.getEmail());			
			if (msgRole.getResult().equals("是管理员")) {
				((Account)msg.getMessage()).setRole("admin");
			} else {
				((Account)msg.getMessage()).setRole("member");
			}
			//把account属性加到session
			session.setAttribute("account", msg.getMessage());
			response.sendRedirect(SUCCESS_VIEW);
		} else {
			request.setAttribute("msg", msg); 
			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
		}
	}
	
}