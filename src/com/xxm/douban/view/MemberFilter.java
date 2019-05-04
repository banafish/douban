package com.xxm.douban.view;

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

import com.xxm.douban.entity.Account;

@WebFilter(
    urlPatterns = { "/articleInfoServlet" }, 
    initParams = { @WebInitParam(name = "LOGIN_VIEW", value = "index.jsp") })
public class MemberFilter implements Filter {
    private String LOGIN_VIEW;
    public void init(FilterConfig config) throws ServletException {
        this.LOGIN_VIEW = config.getInitParameter("LOGIN_VIEW");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Account account = (Account)req.getSession().getAttribute("account");
        
        //获取method
        String method = req.getParameter("method");
        //如果没有就设成空字符串
        if (method == null) {
        	method = "";
        }
        
        if (!method.equals("") && account != null) {//如果请求有method并且登录了
        	//如果method有需要管理权限的操作
        	if (method.equals("delete") || method.equals("setHot") || method.equals("cancelHot")) {
        		if (account.getRole().equals("admin")) {//判断是否为管理员
        			chain.doFilter(request, response);//不是就拦截请求
        		}
        	} else {
        		chain.doFilter(request, response);
        	}
        	
        } else if (!method.equals("") && account == null) {//有method，没有登录
        	HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(LOGIN_VIEW);//重定向
        } else {
        	chain.doFilter(request, response);//无method
        }
        
    }

    public void destroy() {
    }

}
