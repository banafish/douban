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
import com.xxm.douban.service.ArticleService;
import com.xxm.douban.util.DateUtil;

/**
 * Servlet implementation class AddArticle
 */
@WebServlet("/addArticle")
public class AddArticle extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");

		// 获取用户属性
		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			response.getWriter().write("请先登录");
			return;
		}

		Article article = new Article();
		article.setUser_email(account.getEmail());
		article.setTitle(request.getParameter("title"));
		article.setType(request.getParameter("contentType"));
		article.setContent(request.getParameter("content"));
		article.setPicture_urls(request.getParameter("pictures"));
		article.setModify_time(DateUtil.currentTime());
	
		ArticleService articleService = (ArticleService) getServletContext().getAttribute("articleService");
		Msg msg = articleService.addArticle(article);
		
		response.getWriter().write(msg.getResult());
		
	}

}
