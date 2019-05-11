package com.xxm.douban.entity;

public class ArticleInfo {
	private String user_email;
	
	private String article_id;
	
	private String good;//点赞
	
	private String collect;//收藏
	
	private String comment;//评论
	
	private String forword;//转发

	
	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getCollect() {
		return collect;
	}

	public void setCollect(String collect) {
		this.collect = collect;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getForword() {
		return forword;
	}

	public void setForword(String forword) {
		this.forword = forword;
	}

	
}
