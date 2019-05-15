package com.xxm.douban.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Article;
import com.xxm.douban.service.ArticleService;

/**
 * Servlet implementation class HomePage
 * 预览文章，主页
 */
@WebServlet("/homePage")
public class HomePage extends HttpServlet {

	private ArticleService articleService;

	private String currentPage;// 当前页码

	private int totalCounts;// 数据总数

	private int totalPages;// 总页数

	private Msg msgCount;// 数据总数的msg

	private Msg msgList;// 获取文章的msg

	private List<Article> list;// 文章集合

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		articleService = (ArticleService) getServletContext().getAttribute("articleService");

		String method = request.getParameter("method");
		// 根据不同的method调不同的方法
		if (method == null || method.equals("getArticleByPage")) {
			getArticleByPage(request, response);
			return;
		}
		if (method.equals("getSearchArticleByPage")) {
			getSearchArticleByPage(request, response);
			return;
		}
		if (method.equals("getTypeArticleByPage")) {
			getTypeArticleByPage(request, response);
			return;
		}

	}

	// 根据当前页数获取文章
	private void getArticleByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取当前页数
		currentPage = request.getParameter("p");
		if (currentPage == null) {
			currentPage = "1";// 如果为空默认请求第一页数据
		}

		Map resultMap = (Map)((Msg)articleService.getArticleByPage(currentPage)).getMessage();
		list = (List<Article>) resultMap.get("articleList");// 数据
		totalCounts = (int) resultMap.get("articleCount");// 数据总数
		totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条

		request.setAttribute("target", "homePage?method=getArticleByPage&");
		request.setAttribute("list", list);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("homePage.jsp").forward(request, response);
	}

	// 搜索文章
	private void getSearchArticleByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前页数
		currentPage = request.getParameter("p");
		// 获取关键词
		String keyWord = request.getParameter("q");

		if (currentPage == null) {
			currentPage = "1";// 如果为空默认请求第一页数据
		}

		msgList = articleService.getSearchArticleByPage(currentPage, keyWord);
		msgCount = articleService.getSearchArticleCount(keyWord);
		list = (List<Article>) msgList.getMessage();// 数据
		totalCounts = (int) msgCount.getMessage();// 数据总数
		totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条

		request.setAttribute("target", "homePage?method=getSearchArticleByPage&q=" + keyWord + "&");
		request.setAttribute("list", list);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("homePage.jsp").forward(request, response);
	}

	// 分类获取文章
	private void getTypeArticleByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前页数
		currentPage = request.getParameter("p");
		// 获取类型
		String type = request.getParameter("type");

		if (currentPage == null) {
			currentPage = "1";// 如果为空默认请求第一页数据
		}

		msgList = articleService.getTypeArticleByPage(currentPage, type);
		msgCount = articleService.getTypeArticleCount(type);
		list = (List<Article>) msgList.getMessage();// 数据
		totalCounts = (int) msgCount.getMessage();// 数据总数
		totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条

		request.setAttribute("target", "homePage?method=getTypeArticleByPage&type=" + type + "&");
		request.setAttribute("list", list);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("homePage.jsp").forward(request, response);
	}

}
