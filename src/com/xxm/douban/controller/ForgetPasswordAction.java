package com.xxm.douban.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.service.ForgetPasswordService;

/**
 * Servlet implementation class ForgetPasswordAction
 */
@WebServlet("/forgetPasswordAction")
public class ForgetPasswordAction extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		resetPassword(request, response);
	}

	/**
	 * 用户在点击邮箱里的链接后，进入该函数
	 * 
	 * @param request
	 * @param response
	 */
	public void resetPassword(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String key = request.getParameter("key");
		ForgetPasswordService fService = new ForgetPasswordService();
		List<String> datasList = new ArrayList<String>();
		datasList = fService.getExplicitCode(key);
		String time = datasList.get(0);
		String userEmail = datasList.get(1);

		// 判断用户点击链接的时间是否过期，链接有效期为10分钟
		if (fService.judgeOfTime(time)) {
			// 把邮箱属性加到session
			session.setAttribute("email", userEmail);
			request.setAttribute("flag", "true");
			
		} else {
			request.setAttribute("msg", "链接已失效，请重新发送");
		}
		
		try {
			request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
