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
			response.sendRedirect("homePage.jsp");
			return;
		}
		
		//获取当前页数
		String currentPage = request.getParameter("p");
		if (currentPage == null) {
			currentPage = "1";//如果为空默认请求第一页数据
		}
		
		Msg msg = articleService.getArticleByPage(currentPage);		
		List<Article> list = (List<Article>)msg.getMessage();//数据
		int totalPages = (int) msgCount.getMessage() / 2;//总页数
		
		request.setAttribute("list", list);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("homePage.jsp").forward(request, response);
		
	}

}
