package com.xxm.douban.service;

import java.util.HashMap;
import java.util.Map;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.ArticleDAO;
import com.xxm.douban.dao.UploadPicDAO;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;

/**
 * 文章
 */
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

	public Msg modifyArticle(Article article) {
		// 判断是否有图片
		if (article.getPicture_urls().length() > 0) {
			UploadPicDAO uploadPicDAO = new UploadPicDAO();
			article.setPicture_urls(uploadPicDAO.uploadPic(article.getPicture_urls(), article.getAuthor_email()));
		}
		return articleDAO.modifyArticle(article);
	}

	// 在限制条件下获取文章总数
	public Msg getArticleCount(String limit) {
		return articleDAO.getArticleCount(limit);
	}

	// 通过当前页数获取文章
	public Msg getArticleByPage(String currentPage) {
		Map map = new HashMap();
		Msg msgCount = getArticleCount("t_article");
		Msg msgCountForword = getArticleCount("t_article_info where forword = 1");
		Msg msgArticle = articleDAO.getArticleByPage(currentPage);
		// 原文章总数加上转发的数量
		int counts = (int) msgCount.getMessage() + (int) msgCountForword.getMessage();

		map.put("articleCount", counts);
		map.put("articleList", msgArticle.getMessage());
		return new Msg(msgArticle.getResult(), map);
	}

	// 通过当前页数获某个用户的取文章
	public Msg getUserArticleByPage(String currentPage, Account account) {
		Map map = new HashMap();
		Msg msgCount = getArticleCount("t_article where author_email = " + "'" + account.getEmail() + "'");

		String limit = "where t_article.author_email = '" + account.getEmail()
				+ "' and t_article.author_email = t_account.email ";
		Msg msgArticle = articleDAO.getUserArticleByPage(currentPage, limit);

		map.put("articleCount", msgCount.getMessage());
		map.put("articleList", msgArticle.getMessage());
		return new Msg(msgArticle.getResult(), map);
	}

	// 获取关注的文章
	public Msg getFollowArticle(String currentPage, Account account) {
		Map map = new HashMap();
		Msg msgCount = getArticleCount("t_article, t_follow where t_follow.user_email = " + "'" + account.getEmail()
				+ "'" + " and t_article.author_email = t_follow.follow_email ");

		String limit = ", t_follow where t_follow.user_email = '" + account.getEmail()
				+ "' and t_article.author_email = t_follow.follow_email "
				+ "and t_article.author_email = t_account.email ";
		Msg msgArticle = articleDAO.getUserArticleByPage(currentPage, limit);

		map.put("articleCount", msgCount.getMessage());
		map.put("articleList", msgArticle.getMessage());
		return new Msg(msgArticle.getResult(), map);
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

	// 删除文章
	public Msg deleteArticle(String id, String email) {
		UploadPicDAO uploadPicDAO = null;
		Msg msgPic = articleDAO.getArticlePics(id, email);

		if (msgPic.getResult().equals("获取删除文章的图片路径成功")) {
			if (((String) msgPic.getMessage()).length() > 0) {// 如果有图片
				uploadPicDAO = new UploadPicDAO();
				Msg msg = uploadPicDAO.deletePic((String) msgPic.getMessage());
				if (msg.getResult().equals("删除图片成功")) {
					return articleDAO.deleteArticle(id, email);
				}
			}
			return articleDAO.deleteArticle(id, email);
		}
		return new Msg("删除失败", null);
	}

	// 通过当前页数获取用户收藏转发点赞的文章
	public Msg getCollectArticleByPage(String currentPage, Account account, String method) {
		Map map = new HashMap();
		Msg msgCount = getArticleCount(
				"t_article_info where " + method + " = 1 and user_email = '" + account.getEmail() + "'");
		Msg msgArticle = articleDAO.getCollectArticleByPage(currentPage, account, method);

		map.put("articleCount", msgCount.getMessage());
		map.put("articleList", msgArticle.getMessage());
		return new Msg(msgArticle.getResult(), map);
	}

}
