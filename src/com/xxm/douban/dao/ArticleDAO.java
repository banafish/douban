package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;

public interface ArticleDAO {
	Msg addArticle(Article article);//添加文章
	
	Msg getArticleByPage(String currentPage);//通过当前页数获取文章
	
	Msg getArticleCount();//获取文章总数
	
	Msg getUserArticleCount(String email);//获取某个用户的文章总数
	
	Msg getUserArticleByPage(String currentPage, Account account);//通过当前页数获取某个用户的文章
	
	Msg getSearchArticleCount(String keyWord);//获取搜索文章总数
	
	Msg getSearchArticleByPage(String currentPage, String keyWord);//搜索文章
	
	Msg getTypeArticleCount(String type);//分类获取文章总数
	
	Msg getTypeArticleByPage(String currentPage, String type);//分类获取文章

}
