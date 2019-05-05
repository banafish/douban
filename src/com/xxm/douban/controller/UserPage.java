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
	private ArticleService articleService;

	private Msg msg;

	private Msg msgCount;

	private Account account;

	private String id;// 文章id

	private String currentPage;// 当前页数

	private List<Article> list;// 文章集合

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		articleService = (ArticleService) getServletContext().getAttribute("articleService");

		// 获取用户属性
		account = (Account) session.getAttribute("account");

		// 获取当前页数
		currentPage = request.getParameter("p");
		if (currentPage == null) {
			currentPage = "1";// 如果为空默认请求第一页数据
		}

		// 获取文章id
		id = request.getParameter("id");
		// 获取请求nethod
		String method = request.getParameter("method");

		// 根据不同的method调不同的方法
		if (method == null) {
			getUserArticleByPage(request, response);
			return;
		}
		if (method.equals("delete")) {
			deleteArticle(request, response);
			return;
		}

	}

	// 删除文章
	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		msg = articleService.deleteArticle(id);

		if (msg.getResult().equals("删除文章成功")) {
			// 响应
			getUserArticleByPage(request, response);
		}
	}

	//获取用户的文章
	private void getUserArticleByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		msgCount = articleService.getUserArticleCount(account.getEmail());
		msg = articleService.getUserArticleByPage(currentPage, account);
		list = (List<Article>) msg.getMessage();// 数据
		int totalCounts = (int) msgCount.getMessage();// 数据总数
		int totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条

		request.setAttribute("target", "userPage?");
		request.setAttribute("list", list);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("userPage.jsp").forward(request, response);
	}

}
