package com.xxm.douban.entity;

public class Article {
	
	private String id;//文章id
	
	private String user_email;//用户邮箱
	
	private String title;//标题
	
	private String type;//类型
	
	private String content;//内容
	
	private String picture_urls;//图片地址
	
	private String modify_time;//修改时间
	
	private String hot;//热门文章
	
	private String avatar;//用户头像
	
	private String name;//用户名称

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

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
