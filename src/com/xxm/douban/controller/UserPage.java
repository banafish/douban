package com.xxm.douban.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;
import com.xxm.douban.service.ArticleService;

/**
 * Servlet implementation class HmoePage
 */
@WebServlet("/userPage")
public class UserPage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArticleService articleService = (ArticleService) getServletContext().getAttribute("articleService");

		// 获取用户属性
		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			response.sendRedirect("index.jsp");
			return;
		}

		// 获取当前页数
		String currentPage = request.getParameter("p");
		if (currentPage == null) {
			currentPage = "1";// 如果为空默认请求第一页数据
		}

		Msg msgCount = articleService.getUserArticleCount(account.getEmail());
		Msg msg = articleService.getUserArticleByPage(currentPage, account);
		List<Article> list = (List<Article>) msg.getMessage();// 数据
		int totalCounts = (int) msgCount.getMessage();// 数据总数
		int totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条

		request.setAttribute("target", "userPage?");
		request.setAttribute("list", list);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("userPage.jsp").forward(request, response);
	}

}
