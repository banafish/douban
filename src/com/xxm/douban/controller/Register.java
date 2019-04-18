package com.xxm.douban.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxm.douban.entity.Account;
import com.xxm.douban.service.UserService;

/**
 * Servlet implementation class Register
 */
@WebServlet(
	    urlPatterns={"/register.do"},
	    initParams={
	        @WebInitParam(name = "SUCCESS_VIEW", value = "index.html"),
	        @WebInitParam(name = "ERROR_VIEW", value = "index.html")
	    }
)
public class Register extends HttpServlet {
	private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        //String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = (Account) getServletContext().getAttribute("account");
        account.setEmail(email);
        account.setName("mea");
        account.setPassword(password);
        
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        userService.add(account);
        request.getRequestDispatcher("register.html").forward(request, response);
    }
}
