package com.xxm.douban.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxm.douban.util.VerifyCodeUtil;

/**
 * Servlet implementation class VerifyCodeServlet
 */
@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         1.生成验证码
         2.把验证码上的文本存在session中
         3.把验证码图片发送给客户端
         */
        VerifyCodeUtil v=new VerifyCodeUtil();     //用我们的验证码类，生成验证码类对象
        BufferedImage image=v.getImage();  //获取验证码
        request.getSession().setAttribute("verifyCode", v.getText()); //将验证码的文本存在session中
        v.output(image, response.getOutputStream());//将验证码图片响应给客户端
    }
}
