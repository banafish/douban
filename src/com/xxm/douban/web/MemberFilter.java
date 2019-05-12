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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
    urlPatterns = { "/userPage", "/userPage.jsp","/articleEdit.jsp", "/douyou.jsp", "/articleInfo",
    		"/addArticle", "/douYou", "/friendServlet"}, 
    initParams = { @WebInitParam(name = "LOGIN_VIEW", value = "index.jsp") })
public class MemberFilter implements Filter {
    private String LOGIN_VIEW;
    public void init(FilterConfig config) throws ServletException {
        this.LOGIN_VIEW = config.getInitParameter("LOGIN_VIEW");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {		
		
		HttpServletRequest req = (HttpServletRequest) request; 
        if(req.getSession().getAttribute("account") != null) {
            chain.doFilter(request, response);
        }
        else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(LOGIN_VIEW);
        }
    }

    public void destroy() {
    }

}
