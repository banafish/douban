package com.xxm.douban.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.service.ForgetPasswordService;
import com.xxm.douban.service.UserService;

/**
 * Servlet implementation class ForgetPassword
 * 密码忘记，发送信息到邮箱
 */
@WebServlet("/forgetPassword")
public class ForgetPassword extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		passwordForgotten(request, response);
	}

	/**
	 * 密码忘记，发送信息到邮箱
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void passwordForgotten(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		ForgetPasswordService fPasswordService = new ForgetPasswordService();
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		Msg msg = userService.isExistAccount(email);

		// 获取上一次发送验证码的时间
		Date time = (Date) session.getAttribute("forgetTime");
		Long cur = new Date().getTime();
		if (time != null && cur - time.getTime() < 60 * 1000) {// 60秒内不能重发
			request.setAttribute("msg", "请在" + (60 - (cur - time.getTime()) / 1000) + "秒后重发");
			request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
			return;
		}

		// 判断用户是否存在
		if (msg.getResult().equals("存在")) {
			Boolean flag = fPasswordService.sendMail(email);

			if (flag) {
				request.setAttribute("msg", "发送成功");
				session.setAttribute("forgetTime", new Date());// 设置发送验证码的时间
			} else {
				request.setAttribute("msg", "发送失败");
			}
		} else {
			request.setAttribute("msg", "该邮箱未注册");
		}

		request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);

	}

}
