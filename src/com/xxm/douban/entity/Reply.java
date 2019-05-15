package com.xxm.douban.entity;

/**
 * 回复信息实体类
 */
public class Reply {
	
	private String id;//回复id
	
	private String comment_id;//评论id
	
	private String reply_email;//回复的邮箱
	
	private String replyer_email;//回复者的邮箱
	
	private String content;//内容
	
	private String good_count;//点赞数
	
	private String time;//时间
	
	private String reply_name;//回复的名字
	
	private String replyer_name;//回复者的名字

	public String getReply_email() {
		return reply_email;
	}

	public void setReply_email(String reply_email) {
		this.reply_email = reply_email;
	}

	public String getReplyer_email() {
		return replyer_email;
	}

	public void setReplyer_email(String replyer_email) {
		this.replyer_email = replyer_email;
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

	public String getReply_name() {
		return reply_name;
	}

	public void setReply_name(String reply_name) {
		this.reply_name = reply_name;
	}

	public String getReplyer_name() {
		return replyer_name;
	}

	public void setReplyer_name(String replyer_name) {
		this.replyer_name = replyer_name;
	}

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
