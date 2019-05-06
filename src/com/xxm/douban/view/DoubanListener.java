package com.xxm.douban.view;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.xxm.douban.dao.AccountDAOJdbcImpl;
import com.xxm.douban.dao.ArticleDAOJdbcImpl;
import com.xxm.douban.dao.ArticleInfoDAOJdbcImpl;
import com.xxm.douban.dao.FriendDAOJdbcImpl;
import com.xxm.douban.entity.Account;
import com.xxm.douban.service.ArticleInfoService;
import com.xxm.douban.service.ArticleService;
import com.xxm.douban.service.FriendService;
import com.xxm.douban.service.UserService;

@WebListener
public class DoubanListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/douban");
			ServletContext context = sce.getServletContext();
			context.setAttribute("userService", new UserService(new AccountDAOJdbcImpl(dataSource)));
			context.setAttribute("articleService", new ArticleService(new ArticleDAOJdbcImpl(dataSource)));
			context.setAttribute("articleInfoService", new ArticleInfoService(new ArticleInfoDAOJdbcImpl(dataSource)));
			context.setAttribute("friendService", new FriendService(new FriendDAOJdbcImpl(dataSource)));
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
