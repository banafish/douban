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
 * 添加文章
 */
@WebServlet("/addArticle")
public class AddArticle extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleService articleService = (ArticleService) getServletContext().getAttribute("articleService");
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		Msg msg = null;

		// 获取用户属性
		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			response.getWriter().write("请先登录");
			return;
		}

		Article article = new Article();
		article.setId(request.getParameter("id"));
		article.setAuthor_email(account.getEmail());
		article.setTitle(request.getParameter("title"));
		article.setType(request.getParameter("contentType"));
		article.setContent(request.getParameter("content"));
		article.setPicture_urls(request.getParameter("pictures"));
		article.setModify_time(DateUtil.currentTime());
	
		//如果有id属性为空字符串就是发布文章
		if (article.getId().equals("")) {
			msg = articleService.addArticle(article);
		} else {
			msg = articleService.modifyArticle(article);			
		}
		
		response.getWriter().write(msg.getResult());
		
	}

}
