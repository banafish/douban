package com.xxm.douban.bean;

/**
 * 
 * @author 肖学明
 *
 */

public class Msg {

	private String result;
	
	private Object message;

	public String getResult() {
		return result;
	}
	

	public Object getMessage() {
		return message;
	}


	public void setResult(String result) {
		this.result = result;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	
	public Msg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Msg(String result, Object message) {
		super();
		this.result = result;
		this.message = message;
	}

	@Override
	public String toString() {
		return "Msg [result=" + result + ", message=" + message + "]";
	}
	
	
	
}
