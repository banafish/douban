package com.xxm.douban.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xxm.douban.bean.MailSenderInfo;
import com.xxm.douban.util.SimpleMailSender;

/**
 * 发重置密码链接
 */
public class ForgetPasswordService {
	/**
	 * 邮箱发送前的配置
	 * @param serverHost
	 * @param serverPort
	 * @param isValidate
	 * @param userName
	 * @param password
	 * @param toMailAddress
	 * @param subtitle
	 * @param contents
	 */
	public void setMail(String serverHost, String serverPort,
			Boolean isValidate, String userName, String password,
			String toMailAddress, String subtitle, String contents) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(serverHost);
		mailInfo.setMailServerPort(serverPort);
		mailInfo.setValidate(isValidate);
		mailInfo.setUserName(userName);
		mailInfo.setPassword(password);
		mailInfo.setFromAddress(userName);
		mailInfo.setToAddress(toMailAddress);
		mailInfo.setSubject(subtitle);
		mailInfo.setContent(contents);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
	}
	
	/**
	 * 发送信息到用户的登录邮箱
	 * 
	 * @param userEmail
	 * @return
	 */
	public Boolean sendMail(String userEmail) {
		Boolean flag = false;
		try {
			// 获取当前系统时间
			Date now = new Date();
			String currentTime = "" + now.getTime();
			
			String urlString = "http://localhost:8080/Douban/forgetPasswordAction?method=resetPassword&key=";
			String link = urlString + currentTime + "@" + userEmail;
			String contents = link;
			//邮箱配置
			String serverHost = "smtp.qq.com";
			String serverPort = "25";
			Boolean isValidate = true;
			String userName = "";//邮箱
			String password = "";//授权码
			String toMailAddress = userEmail;
			String subtitle = "豆瓣注册账号密码找回 ";
			this.setMail(serverHost, serverPort, isValidate, userName, password, toMailAddress, subtitle, contents);
			
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	//链接有效期为10分钟
	public Boolean judgeOfTime(String time) {
		long beginTime = Long.valueOf(time);
		Date now = new Date();
		if (now.getTime() - beginTime < 10*60*1000) {
			return true;
		}
		return false;
	}

	public List<String> getExplicitCode(String key) {
		List<String> list = new ArrayList();
		String time = key.substring(0, key.indexOf("@"));
		String email = key.substring(key.indexOf("@") + 1);
		list.add(time);
		list.add(email);
		return list;
	}
}
