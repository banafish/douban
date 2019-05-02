package com.xxm.douban.dao;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Article;

public interface ArticleDAO {
	Msg addArticle(Article article);//添加文章
	
	Msg getArticleByPage(String currentPage);//通过当前页数获取文章
	
	Msg getArticleCount();//获取文章总数

}
