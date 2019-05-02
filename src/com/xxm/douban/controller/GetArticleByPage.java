package com.xxm.douban.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Article;
import com.xxm.douban.service.ArticleService;

/**
 * Servlet implementation class GetArticleByPage
 */
@WebServlet("/getArticleByPage")
public class GetArticleByPage extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleService articleService = (ArticleService) getServletContext().getAttribute("articleService");
		
		Msg msgCount = articleService.getArticleCount();
		
		if (msgCount.getResult().equals("获取文章总数失败")) {
			return;
		}
		
		Msg msg = articleService.getArticleByPage("2");		
		List<Article> list = (List<Article>)msg.getMessage();
		
		int counts = (int) msgCount.getMessage();
	}

}
