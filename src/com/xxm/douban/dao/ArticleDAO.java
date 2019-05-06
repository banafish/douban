package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;

public interface ArticleDAO {
	Msg addArticle(Article article);//添加文章
	
	Msg getArticleByPage(String currentPage);//通过当前页数获取文章
	
	Msg getArticleCount(String limit);//在限制条件下获取文章总数
	
	Msg getUserArticleByPage(String currentPage, Account account);//通过当前页数获取某个用户的文章
	
	Msg getSearchArticleCount(String keyWord);//获取搜索文章总数
	
	Msg getSearchArticleByPage(String currentPage, String keyWord);//搜索文章
	
	Msg getTypeArticleCount(String type);//分类获取文章总数
	
	Msg getTypeArticleByPage(String currentPage, String type);//分类获取文章
	
	Msg setArticleHot(String id, int hot);// 置顶/取消置顶
	
	Msg deleteArticle(String id);// 删除文章
	
	Msg getArticlePics(String id);// 获取删除文章的图片路径
	
	Msg getCollectArticleByPage(String currentPage, Account account, String method);//获取收藏转发的文章

}
