package com.xxm.douban.entity;

public class Friend {
	
	private String id;
	
	private String host_email;//主邮箱
	
	private String guest_email;//对方的邮箱
	
	private String msg;//信息
	
	private String time;//时间
	
	private String statue;//状态
	
	private String table;//要操作的表
	
	private String name;//名字
	
	private String avatar;//头像
	
	private String sign;//签名
	
	private String host_black;//黑名单
	
	private String guest_black;//黑名单

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost_email() {
		return host_email;
	}

	public void setHost_email(String host_email) {
		this.host_email = host_email;
	}

	public String getGuest_email() {
		return guest_email;
	}

	public void setGuest_email(String guest_email) {
		this.guest_email = guest_email;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getHost_black() {
		return host_black;
	}

	public void setHost_black(String host_black) {
		this.host_black = host_black;
	}

	public String getGuest_black() {
		return guest_black;
	}

	public void setGuest_black(String guest_black) {
		this.guest_black = guest_black;
	}

}
