package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;

public interface ArticleInfoDAO {
	Msg getArticleInfo(String id, String user_email);//获取文章全部信息
	
	Msg getArticleInfoDetail(String id);//统计文章点赞等数量
	
	Msg setArticleInfoGCF(String id, String user_email, String method, String value);//点赞收藏转发

}
