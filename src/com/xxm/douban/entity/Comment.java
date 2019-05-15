package com.xxm.douban.entity;

import java.util.List;

/**
 * 评论信息实体类
 */
public class Comment {
	private String id;//评论id
	
	private String article_id;//文章id
	
	private String user_email;//评论者邮箱
	
	private String content;//评论内容
	
	private String good_count;//点赞数量
	
	private String time;//评论时间
	
	private String name;//名字
	
	private String avatar;//头像
	
	private List<Reply> replys;//回复

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGood_count() {
		return good_count;
	}

	public void setGood_count(String good_count) {
		this.good_count = good_count;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}
	
	

}
