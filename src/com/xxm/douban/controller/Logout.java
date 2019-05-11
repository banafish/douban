package com.xxm.douban.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//移除session中的account属性
        session.removeAttribute("account");
        //关了自动登录
        Cookie cookie = new Cookie("autoLogin", "off");
        response.addCookie(cookie);
        // 重定向到首页
        response.sendRedirect("index.jsp");
	}

}
