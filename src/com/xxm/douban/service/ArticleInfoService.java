package com.xxm.douban.service;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.ArticleInfoDAO;

public class ArticleInfoService {
	private ArticleInfoDAO articleInfoDAO;

	public ArticleInfoService(ArticleInfoDAO articleInfoDAO) {
		this.articleInfoDAO = articleInfoDAO;
	}

	// 获取文章全部信息
	public Msg getArticleInfo(String id, String user_email) {
		return articleInfoDAO.getArticleInfo(id, user_email);
	}

	//统计文章点赞等数量
	public Msg getArticleInfoDetail(String id) {
		return articleInfoDAO.getArticleInfoDetail(id);
	} 
	
	//点赞
	public Msg setArticleInfoGCF(String id, String user_email, String method, String value) {		
		return articleInfoDAO.setArticleInfoGCF(id, user_email, method, value);
	}
}
