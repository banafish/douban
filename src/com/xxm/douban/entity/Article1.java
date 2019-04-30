package com.xxm.douban.entity;

public class Article1 {
	
	private String user_email;//用户邮箱
	
	private String title;//标题
	
	private String type;//类型
	
	private String content;//内容
	
	private String picture_urls;//图片地址
	
	private String modify_time;//修改时间

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture_urls() {
		return picture_urls;
	}

	public void setPicture_urls(String picture_urls) {
		this.picture_urls = picture_urls;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	
}
