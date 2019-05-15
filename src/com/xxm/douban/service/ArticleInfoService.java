package com.xxm.douban.service;

import java.util.ArrayList;
import java.util.List;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.dao.ArticleInfoDAO;
import com.xxm.douban.entity.Comment;
import com.xxm.douban.entity.Reply;

/**
 * 文章点赞收藏转发
 */
public class ArticleInfoService {
	private ArticleInfoDAO articleInfoDAO;

	public ArticleInfoService(ArticleInfoDAO articleInfoDAO) {
		this.articleInfoDAO = articleInfoDAO;
	}

	// 获取文章全部信息
	public Msg getArticleInfo(String id, String user_email) {
		return articleInfoDAO.getArticleInfo(id, user_email);
	}

	// 统计文章点赞等数量
	public Msg getArticleInfoDetail(String id) {
		return articleInfoDAO.getArticleInfoDetail(id);
	}

	// 点赞
	public Msg setArticleInfoGCF(String id, String user_email, String method, String value) {
		return articleInfoDAO.setArticleInfoGCF(id, user_email, method, value);
	}

	// 写评论
	public Msg setComment(Comment comment) {
		return articleInfoDAO.setComment(comment);
	}

	// 获取评论	
	public Msg getComment(String currentPage, String id) {
		List<Comment> comments = (List<Comment>) articleInfoDAO.getComment(currentPage, id).getMessage();
		if (comments == null) {
			return new Msg("无评论", null);
		}
		
		//获取回复
		for (int i = 0; i < comments.size(); i++) {
			Msg msg = getReply(comments.get(i).getId());
			comments.get(i).setReplys((List<Reply>) msg.getMessage());
		}
		return new Msg("获取评论成功", comments);
	}
	
	// 获取评论数量	
	public Msg getCommentCount(String id) {
		return articleInfoDAO.getCommentCount(id);
	}

	// 点赞评论	
	public Msg setCommentGood(String id, String counts) {
		int count;
		if (counts != null) {
			if (counts.matches("^[0-9]*")) {
				count = Integer.valueOf(counts) + 1;
				return articleInfoDAO.setCommentGood(id, count + "");
			}
			return new Msg("不是数字", null);
		} 
		return new Msg("点赞失败", null);
	}
	
	//回复
	public Msg setReply(Reply reply) {
		return articleInfoDAO.setReply(reply);
	}
	
	//赞回复
	public Msg setReplyGood(String id, String counts) {
		int count;
		if (counts != null) {
			if (counts.matches("^[0-9]*")) {
				count = Integer.valueOf(counts) + 1;
				return articleInfoDAO.setReplyGood(id, count + "");
			}
			return new Msg("不是数字", null);
		} 
		return new Msg("点赞失败", null);
	}
	
	//获取回复
	private Msg getReply(String id) {
		return articleInfoDAO.getReply(id);
	}
	
}
