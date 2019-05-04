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
		// 判断是否有图片
		if (article.getPicture_urls().length() > 0) {
			UploadPicDAO uploadPicDAO = new UploadPicDAO();
			article.setPicture_urls(uploadPicDAO.uploadPic(article.getPicture_urls(), article.getAuthor_email()));
		}

		return articleDAO.addArticle(article);

	}

	// 通过当前页数获取文章
	public Msg getArticleByPage(String currentPage) {
		return articleDAO.getArticleByPage(currentPage);
	}

	// 获取文章总数
	public Msg getArticleCount() {
		return articleDAO.getArticleCount();
	}

	// 通过当前页数获某个用户的取文章
	public Msg getUserArticleByPage(String currentPage, Account account) {
		return articleDAO.getUserArticleByPage(currentPage, account);
	}

	// 获取某个用户的文章总数
	public Msg getUserArticleCount(String email) {
		return articleDAO.getUserArticleCount(email);
	}

	// 获取搜索文章总数
	public Msg getSearchArticleCount(String keyWord) {
		return articleDAO.getSearchArticleCount(keyWord);
	}

	// 搜索文章
	public Msg getSearchArticleByPage(String currentPage, String keyWord) {
		return articleDAO.getSearchArticleByPage(currentPage, keyWord);
	}

	// 分类获取文章总数
	public Msg getTypeArticleCount(String type) {
		return articleDAO.getTypeArticleCount(type);
	}

	// 分类获取文章
	public Msg getTypeArticleByPage(String currentPage, String type) {
		return articleDAO.getTypeArticleByPage(currentPage, type);
	}
	
	// 置顶/取消置顶
	public Msg setArticleHot(String id, int hot) {
		return articleDAO.setArticleHot(id, hot);
	}
}
