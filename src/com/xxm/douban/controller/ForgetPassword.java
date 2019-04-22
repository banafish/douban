package com.xxm.douban.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.service.ForgetPasswordService;
import com.xxm.douban.service.UserService;

/**
 * Servlet implementation class ForgetPassword
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
	 */
	public void passwordForgotten(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		ForgetPasswordService fPasswordService = new ForgetPasswordService();
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		Msg msg = userService.isExistAccount(email);
		
		//判断用户是否存在
		if (msg.getResult().equals("存在")) {
			Boolean flag = fPasswordService.sendMail(email);
			
			if (flag) {
				request.setAttribute("msg", "发送成功");
			} else {
				request.setAttribute("msg", "发送失败");
			}
		} else {
			request.setAttribute("msg", "该邮箱未注册");
		}
			
		try {
			request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
		} catch (ServletException | IOException e1) {
			e1.printStackTrace();
		}

	}

}
