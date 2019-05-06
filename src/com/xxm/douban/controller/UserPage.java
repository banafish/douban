package com.xxm.douban.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;
import com.xxm.douban.service.ArticleInfoService;
import com.xxm.douban.service.ArticleService;

/**
 * Servlet implementation class HmoePage
 */
@WebServlet("/userPage")
public class UserPage extends HttpServlet {
	private ArticleService articleService;
	
	private ArticleInfoService articleInfoService;

	private Msg msg;

	private Account account;

	private String id;// 文章id

	private String currentPage;// 当前页数

	private List<Article> list;// 文章集合

	private Map resultMap;// 结果map

	private int totalCounts;// 数据总数

	private int totalPages;// 总页数，每页四条

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		articleService = (ArticleService) getServletContext().getAttribute("articleService");
		articleInfoService = (ArticleInfoService) getServletContext().getAttribute("articleInfoService");

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
			getUserArticleByPage();
			method = "";
			request.setAttribute("delete", "delete");// 设置删除文章的类型
		}
		// 查看收藏的文章
		if (method.equals("getCollect")) {
			getCollectArticle();
			request.setAttribute("delete", "deleteCollect");
		}
		// 查看转发的文章
		if (method.equals("getForword")) {
			getForwordArticle();
			request.setAttribute("delete", "deleteForword");
		}
		// 删除文章
		if (method.equals("delete")) {
			msg = articleService.deleteArticle(id);
			if (msg.getResult().equals("删除文章成功")) {
				// 响应
				getUserArticleByPage();
			}
		}
		//取消收藏
		if (method.equals("deleteCollect")) {
			msg = articleInfoService.setArticleInfoGCF(id, account.getEmail(), "collect", null);
			if (msg.getResult().equals("操作成功")) {
				// 响应
				getCollectArticle();
				request.setAttribute("delete", "deleteCollect");
			}
		}
		//取消转发
		if (method.equals("deleteForword")) {
			msg = articleInfoService.setArticleInfoGCF(id, account.getEmail(), "forword", null);
			if (msg.getResult().equals("操作成功")) {
				// 响应
				getForwordArticle();
				request.setAttribute("delete", "deleteForword");
			}
		}

		// 响应
		request.setAttribute("target", "userPage?");
		request.setAttribute("list", list);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("userPage.jsp").forward(request, response);

	}

	// 查看转发的文章
	private void getForwordArticle() {
		resultMap = (Map) ((Msg) articleService.getCollectArticleByPage(currentPage, account, "forword")).getMessage();
		list = (List<Article>) resultMap.get("articleList");// 数据
		totalCounts = (int) resultMap.get("articleCount");// 数据总数
		totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条

	}

	// 查看收藏的文章
	private void getCollectArticle() {
		resultMap = (Map) ((Msg) articleService.getCollectArticleByPage(currentPage, account, "collect")).getMessage();
		list = (List<Article>) resultMap.get("articleList");// 数据
		totalCounts = (int) resultMap.get("articleCount");// 数据总数
		totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条
	}

	// 获取用户的文章
	private void getUserArticleByPage() {
		resultMap = (Map) ((Msg) articleService.getUserArticleByPage(currentPage, account)).getMessage();
		list = (List<Article>) resultMap.get("articleList");// 数据
		totalCounts = (int) resultMap.get("articleCount");// 数据总数
		totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条

	}

}
