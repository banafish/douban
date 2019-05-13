package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Comment;
import com.xxm.douban.entity.Reply;

public interface ArticleInfoDAO {
	Msg getArticleInfo(String id, String user_email);//获取文章全部信息
	
	Msg getArticleInfoDetail(String id);//统计文章点赞等数量
	
	Msg setArticleInfoGCF(String id, String user_email, String method, String value);//点赞收藏转发
	
	Msg setComment(Comment comment);//写评论
	
	Msg getComment(String currentPage, String id);//获取评论
	
	Msg getCommentCount(String id);//获取评论数量
	
	Msg setCommentGood(String id, String counts);//点赞评论
	
	Msg getReply(String id);//获取回复
	
	Msg setReply(Reply reply);//回复
	
	Msg setReplyGood(String id, String counts);//赞回复

}
