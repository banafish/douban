package com.xxm.douban.service;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.ArticleDAO;
import com.xxm.douban.dao.UploadPicDAO;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article1;

public class ArticleService {
	private ArticleDAO articleDAO;

	public ArticleService(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	// 添加文章
	public Msg addArticle(Article1 article) {
		UploadPicDAO uploadPaicDAO = new UploadPicDAO();
		return articleDAO.addArticle(uploadPaicDAO.uploadPic(article));
	}
}
