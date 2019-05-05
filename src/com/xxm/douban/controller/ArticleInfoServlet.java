package com.xxm.douban.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;
import com.xxm.douban.entity.ArticleInfo;
import com.xxm.douban.service.ArticleInfoService;
import com.xxm.douban.service.ArticleService;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/articleInfoServlet")
public class ArticleInfoServlet extends HttpServlet {
	private ArticleInfoService articleInfoService;
	
	private ArticleService articleService;

	private Msg msg;

	private Account account;

	private String id;// 文章id

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		articleInfoService = (ArticleInfoService) getServletContext().getAttribute("articleInfoService");
		articleService = (ArticleService) getServletContext().getAttribute("articleService");
		HttpSession session = request.getSession();

		// 获取用户属性
		account = (Account) session.getAttribute("account");

		// 获取文章id
		id = request.getParameter("id");

		String method = request.getParameter("method");
		String realMethod = null;// 真实method,防止sql注入

		// 根据不同的method调不同的方法
		if (method == null) {
			getArticleInfo(request, response);
			return;
		}
		
		//删除文章
		if (method.equals("delete")) {
			deleteArticle(request, response);
			return;
		}

		// 置顶/取消置顶
		if (method.equals("setHot")) {
			setArticleHot(request, response, 1);// 1代表置顶
			return;
		}
		if (method.equals("cancelHot")) {
			setArticleHot(request, response, 0);// 0代表取消置顶
			return;
		}

		// 点赞收藏转发
		if (method.equals("good")) {
			realMethod = "good";
		}
		if (method.equals("collect")) {
			realMethod = "collect";
		}
		if (method.equals("forword")) {
			realMethod = "forword";
		}
		setArticleInfoGCF(request, response, realMethod);

	}
	
	//删除文章
	private void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		msg = articleService.deleteArticle(id);
		
		if (msg.getResult().equals("删除文章成功")) {
			// 响应
			getArticleInfo(request, response);
		}
	}


	// 点赞收藏转发
	private void setArticleInfoGCF(HttpServletRequest request, HttpServletResponse response, String method)
			throws ServletException, IOException {

		// 获取文章全部信息
		msg = articleInfoService.setArticleInfoGCF(id, account.getEmail(), method);
		// 响应
		getArticleInfo(request, response);
	}

	// 置顶/取消置顶
	private void setArticleHot(HttpServletRequest request, HttpServletResponse response, int hot)
			throws ServletException, IOException {

		msg = articleService.setArticleHot(id, hot);
		// 响应
		getArticleInfo(request, response);
	}

	// 获取文章全部信息
	private void getArticleInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取文章全部信息
		String user_email = null;
		if (account != null) {
			user_email = account.getEmail();
		}
		
		msg = articleInfoService.getArticleInfo(id, user_email);
		Article article = (Article) msg.getMessage();
		request.setAttribute("article", article);

		// 统计文章点赞等数量
		msg = articleInfoService.getArticleInfoDetail(id);
		ArticleInfo articleInfoDetail = (ArticleInfo) msg.getMessage();
		request.setAttribute("articleInfoDetail", articleInfoDetail);

		request.getRequestDispatcher("articleInfo.jsp").forward(request, response);
	}

}
