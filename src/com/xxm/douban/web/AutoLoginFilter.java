package com.xxm.douban.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxm.douban.entity.Account;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter(urlPatterns = { "/index.jsp" }, initParams = { @WebInitParam(name = "LOGIN_VIEW", value = "index.jsp"),
		@WebInitParam(name = "HOME_VIEW", value = "homePage?p=1") })
public class AutoLoginFilter implements Filter {

	private String LOGIN_VIEW;

	private String HOME_VIEW;

	public void init(FilterConfig config) throws ServletException {
		this.LOGIN_VIEW = config.getInitParameter("LOGIN_VIEW");
		this.HOME_VIEW = config.getInitParameter("HOME_VIEW");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Account account;
		String value = "";
		
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				value = cookie.getValue();
				if (name.equals("autoLogin")) {
					break;
				}
			}
		}

		// 判断值是否为off，如果是就不自动登录
		if (!value.equals("") && !value.equals("off")) {
			String[] values = value.split(",");
			account = new Account();
			account.setEmail(values[0]);
			account.setName(values[1]);
			account.setAvatar(values[2]);

			req.getSession().setAttribute("account", account);
			resp.sendRedirect(HOME_VIEW);// 重定向
		} 
		chain.doFilter(request, response);
			
	}

	public void destroy() {
	}
}
