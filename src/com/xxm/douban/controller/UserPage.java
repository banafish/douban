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
import com.xxm.douban.service.FriendService;

/**
 * Servlet implementation class HmoePage
 */
@WebServlet("/userPage")
public class UserPage extends HttpServlet {
	private ArticleService articleService;

	private ArticleInfoService articleInfoService;

	private FriendService friendService;

	private Msg msg;

	private Account account;

	private String id;// 操作对象的id

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
		friendService = (FriendService) getServletContext().getAttribute("friendService");

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
			getUserArticleByPage(request);
			method = "";
		}

		// 查看关注的文章
		if (method.equals("getFollowArticle")) {
			getFollowArticle(request);
		}
		// 查看收藏的文章
		if (method.equals("getCollect")) {
			getCollectArticle(request);
		}
		// 查看赞过的文章
		if (method.equals("getGood")) {
			getGoodArticle(request);
		}
		// 查看转发的文章
		if (method.equals("getForword")) {
			getForwordArticle(request);
		}

		// 删除文章
		if (method.equals("delete")) {
			msg = articleService.deleteArticle(id);
			if (msg.getResult().equals("删除文章成功")) {
				// 响应
				getUserArticleByPage(request);
			}
		}
		// 取消收藏
		if (method.equals("deleteCollect")) {
			msg = articleInfoService.setArticleInfoGCF(id, account.getEmail(), "collect", null);
			if (msg.getResult().equals("操作成功")) {
				// 响应
				getCollectArticle(request);
			}
		}
		// 取消点赞
		if (method.equals("deleteGood")) {
			msg = articleInfoService.setArticleInfoGCF(id, account.getEmail(), "good", null);
			if (msg.getResult().equals("操作成功")) {
				// 响应
				getGoodArticle(request);
			}
		}
		// 取消转发
		if (method.equals("deleteForword")) {
			msg = articleInfoService.setArticleInfoGCF(id, account.getEmail(), "forword", null);
			if (msg.getResult().equals("操作成功")) {
				// 响应
				getForwordArticle(request);
			}
		}

		//修改文章
		if (method.equals("modifyArticle")) {
			msg = articleInfoService.getArticleInfo(id, account.getEmail());
			request.setAttribute("article", (Article)msg.getMessage());
			request.getRequestDispatcher("articleEdit.jsp").forward(request, response);
			return;
		}

		list = (List<Article>) resultMap.get("articleList");// 数据
		totalCounts = (int) resultMap.get("articleCount");// 数据总数
		totalPages = ((totalCounts % 4 == 0) ? (totalCounts / 4) : (totalCounts / 4 + 1));// 总页数，每页四条

		// 响应
		request.setAttribute("list", list);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("userPage.jsp").forward(request, response);

	}


	// 查看关注的文章
	private void getFollowArticle(HttpServletRequest request) {
		resultMap = (Map) ((Msg) articleService.getFollowArticle(currentPage, account)).getMessage();
		request.setAttribute("target", "userPage?method=getFollowArticle&");
	}

	// 查看赞过的文章
	private void getGoodArticle(HttpServletRequest request) {
		resultMap = (Map) ((Msg) articleService.getCollectArticleByPage(currentPage, account, "good")).getMessage();
		request.setAttribute("delete", "deleteGood");
		request.setAttribute("target", "userPage?method=getGood&");
	}

	// 查看转发的文章
	private void getForwordArticle(HttpServletRequest request) {
		resultMap = (Map) ((Msg) articleService.getCollectArticleByPage(currentPage, account, "forword")).getMessage();
		request.setAttribute("delete", "deleteForword");
		request.setAttribute("target", "userPage?method=getForword&");

	}

	// 查看收藏的文章
	private void getCollectArticle(HttpServletRequest request) {
		resultMap = (Map) ((Msg) articleService.getCollectArticleByPage(currentPage, account, "collect")).getMessage();
		request.setAttribute("delete", "deleteCollect");
		request.setAttribute("target", "userPage?method=getCollect&");
	}

	// 获取用户的文章
	private void getUserArticleByPage(HttpServletRequest request) {
		resultMap = (Map) ((Msg) articleService.getUserArticleByPage(currentPage, account)).getMessage();
		request.setAttribute("delete", "delete");// 设置删除文章的类型
		request.setAttribute("target", "userPage?");
	}

}
