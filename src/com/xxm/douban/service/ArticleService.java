package com.xxm.douban.service;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.ArticleDAO;
import com.xxm.douban.dao.UploadPicDAO;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;

public class ArticleService {
	private ArticleDAO articleDAO;

	public ArticleService(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	// 添加文章
	public Msg addArticle(Article article) {
		//判断是否有图片
		if (article.getPicture_urls() != null) {
			UploadPicDAO uploadPicDAO = new UploadPicDAO();
			article.setPicture_urls(uploadPicDAO.uploadPic(article.getPicture_urls(), article.getUser_email()));
		} 
			
		return articleDAO.addArticle(article);	
		
	}
	
	//通过当前页数获取文章
	public Msg getArticleByPage(String currentPage) {
		return articleDAO.getArticleByPage(currentPage);
	}
	
	//获取文章总数
	public Msg getArticleCount() {
		return articleDAO.getArticleCount();
	}
	
}
