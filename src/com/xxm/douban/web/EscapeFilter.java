package com.xxm.douban.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns ={"/accountInfo", "/articleInfoServlet", "/chatServlet", "/douYou",
	"/forgetPassword", "/forgetPasswordAction", "/friendServlet1", "/homePage",
	"/login", "/logout", "/register", "/resetPassword", "/userPage"})
public class EscapeFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requestWrapper = new EscapeWrapper((HttpServletRequest) request);
		chain.doFilter(requestWrapper, response);
	}

	public void destroy() {
	}
}
