package com.xxm.douban.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Friend;
import com.xxm.douban.service.FriendService;
import com.xxm.douban.util.DateUtil;

/**
 * Servlet implementation class DouYou
 */
@WebServlet("/douYou")
public class DouYou extends HttpServlet {
	private FriendService friendService;

	private Friend friend;

	private Account account;

	private Msg result;

	private String msg;// 信息

	private String guest_email;// 对方的邮箱

	private Msg resultCount;// 总数

	private String currentPage;// 当前页数

	private List<Friend> list;// 集合

	private int totalCounts;// 数据总数

	private int totalPages;// 总页数，每页四条
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		friendService = (FriendService) getServletContext().getAttribute("friendService");

		// 获取用户属性
		account = (Account) session.getAttribute("account");
		// 获取对方的邮箱
		guest_email = request.getParameter("guest_email");
		// 获取信息
		msg = request.getParameter("msg");
		
		result = friendService.inBlack(account.getEmail(), guest_email);
		if (result.getResult().equals("不在黑名单")) {
			friend = new Friend();
			friend.setHost_email(account.getEmail());
			friend.setGuest_email(guest_email);
			friend.setMsg(msg);
			friend.setStatue("1");//1表示未读
			friend.setTable("t_douyou");
			friend.setTime(DateUtil.currentTime());
			result = friendService.insertFriend(friend);
			request.setAttribute("result", "发送成功");
		} else {
			request.setAttribute("result", "在黑名单内不能发豆邮");
		}
		request.getRequestDispatcher("douyou.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		friendService = (FriendService) getServletContext().getAttribute("friendService");

		// 获取用户属性
		account = (Account) session.getAttribute("account");
		// 获取对方的邮箱
		guest_email = request.getParameter("guest_email");
		// 获取当前页数
		currentPage = request.getParameter("p");
		if (currentPage == null) {
			currentPage = "1";// 如果为空默认请求第一页数据
		}
		// 获取方法
		String method = request.getParameter("method");
		if (method == null) {
			getDouYou(request, response);
			method = "";
		}
		//删除豆邮
		if (method.equals("deleteDouYou")) {
			friendService.deleteDouYou(request.getParameter("id"), account.getEmail());
			getDouYou(request, response);
		}
		
		totalCounts = (int) resultCount.getMessage();
		totalPages = ((totalCounts % 6 == 0) ? (totalCounts / 6) : (totalCounts / 6 + 1));// 总页数，每页6条
		request.setAttribute("douYouList", (List<Friend>) result.getMessage());
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("douyou.jsp").forward(request, response);
	}

	//豆邮列表
	private void getDouYou(HttpServletRequest request, HttpServletResponse response) {
		resultCount = friendService.getDouYouCount(account.getEmail());
		result = friendService.getDouYou(currentPage, account.getEmail());
		request.setAttribute("target", "douYou?");
	}


}
