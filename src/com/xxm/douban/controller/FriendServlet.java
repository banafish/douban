package com.xxm.douban.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;
import com.xxm.douban.entity.Friend;
import com.xxm.douban.service.ArticleService;
import com.xxm.douban.service.FriendService;
import com.xxm.douban.util.DateUtil;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/friendServlet")
public class FriendServlet extends HttpServlet {
	private FriendService friendService;

	private Friend friend;

	private String guest_email;

	private Account account;

	private Msg result;// 好友

	private Msg resultCount;// 总数

	private String msg;// 信息

	private String currentPage;// 当前页数

	private List<Friend> list;// 集合

	private int totalCounts;// 数据总数

	private int totalPages;// 总页数，每页6条

	private List<String> groups;// 分组

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		friendService = (FriendService) getServletContext().getAttribute("friendService");

		// 获取当前页数
		currentPage = request.getParameter("p");
		if (currentPage == null) {
			currentPage = "1";// 如果为空默认请求第一页数据
		}
		// 获取用户属性
		account = (Account) session.getAttribute("account");
		// 获取对方的邮箱
		guest_email = request.getParameter("guest_email");
		// 获取信息
		msg = request.getParameter("msg");
		// 获取方法
		String method = request.getParameter("method");
		if (method == null) {
			method = "";
		}

		// 查看朋友
		if (method.equals("getFriendList")) {
			getFriendList(request, response);
		}
		// 分类查看朋友
		if (method.equals("getFriendByGroup")) {
			getFriendByGroup(request, response);
		}
		// 查看好友申请
		if (method.equals("getApply")) {
			getApply(request, response);
		}
		// 查看黑名单
		if (method.equals("getBlack")) {
			getBlack(request, response);
		}
		// 查看关注的人
		if (method.equals("getFollow")) {
			getFollow(request, response);
		}

		// 取消关注
		if (method.equals("cancelFollow")) {
			friendService.cancelFollow(account.getEmail(), guest_email);
			getFollow(request, response);
		}
		// 申请好友
		if (method.equals("applyFriend")) {
			applyFriend(request, response);
		}
		// 通过好友申请
		if (method.equals("allow")) {
			allowApply(request, response);
		}
		// 删除好友
		if (method.equals("deleteFriend")) {
			deleteFriend(request, response);
		}
		// 拒绝好友申请
		if (method.equals("denyApply")) {
			friendService.deleteFriend(guest_email, account.getEmail());// 删除对方的好友申请
			getApply(request, response);
		}
		// 拉黑
		if (method.equals("setBlack")) {
			// 设置自己的
			friend = new Friend();
			friend.setHost_email(account.getEmail());
			friend.setGuest_email(guest_email);
			friend.setHost_black("1");
			friend.setGuest_black("0");
			friendService.setBlack(friend);
			// 设置对方的
			friend.setHost_email(guest_email);
			friend.setGuest_email(account.getEmail());
			friend.setHost_black("0");
			friend.setGuest_black("1");
			friendService.setBlack(friend);

			getFriendList(request, response);
		}
		// 取消拉黑
		if (method.equals("cancelBlack")) {
			// 设置自己的
			friend = new Friend();
			friend.setHost_email(account.getEmail());
			friend.setGuest_email(guest_email);
			friend.setHost_black("0");
			friend.setGuest_black("0");
			friendService.setBlack(friend);
			// 设置对方的
			friend.setHost_email(guest_email);
			friend.setGuest_email(account.getEmail());
			friend.setHost_black("0");
			friend.setGuest_black("0");
			friendService.setBlack(friend);

			getBlack(request, response);
		}

		totalCounts = (int) resultCount.getMessage();
		totalPages = ((totalCounts % 6 == 0) ? (totalCounts / 6) : (totalCounts / 6 + 1));// 总页数，每页6条
		request.setAttribute("groups", groups);
		request.setAttribute("friendList", (List<Friend>) result.getMessage());
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("userPage.jsp").forward(request, response);

	}

	// 查看关注的人
	private void getFollow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		resultCount = friendService.getFollowCount(account.getEmail());
		result = friendService.getFollow(currentPage, account.getEmail());

		request.setAttribute("show", "follow");
		request.setAttribute("target", "friendServlet?method=getFollow&");
	}

	// 查看黑名单
	private void getBlack(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		resultCount = friendService.getBlackCount(account.getEmail());
		result = friendService.getBlack(currentPage, account.getEmail());

		request.setAttribute("show", "black");
		request.setAttribute("target", "friendServlet?method=getBlack&");
	}

	// 查看好友申请
	private void getApply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		resultCount = friendService.getApplyCount(account.getEmail());
		result = friendService.getApply(currentPage, account.getEmail());

		// 用户好友分组信息
		Msg msgGroups = friendService.getGroup(account.getEmail());
		groups = (List<String>) msgGroups.getMessage();

		request.setAttribute("show", "apply");
		request.setAttribute("target", "friendServlet?method=getApply&");
	}

	// 查看朋友
	private void getFriendList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		resultCount = friendService.getFriendCount(account.getEmail());
		result = friendService.getFriendList(currentPage, account.getEmail());

		// 用户好友分组信息
		Msg msgGroups = friendService.getGroup(account.getEmail());
		groups = (List<String>) msgGroups.getMessage();

		request.setAttribute("show", "friend");
		request.setAttribute("target", "friendServlet?method=getFriendList&");
	}

	// 通过分类取得朋友列表
	private void getFriendByGroup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String group = request.getParameter("group");
		resultCount = friendService.getFriendGroupCount(account.getEmail(), group);
		result = friendService.getFriendByGroup(currentPage, account.getEmail(), group);

		// 用户好友分组信息
		Msg msgGroups = friendService.getGroup(account.getEmail());
		groups = (List<String>) msgGroups.getMessage();

		request.setAttribute("show", "friend");
		request.setAttribute("target", "friendServlet?method=getFriendByGroup&group=" + group + "&");
	}

	// 删除好友
	private void deleteFriend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		friendService.deleteFriend(account.getEmail(), guest_email);// 删除自己的
		friendService.deleteFriend(guest_email, account.getEmail());// 删除对方的
		getFriendList(request, response);
	}

	// 通过好友申请
	private void allowApply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 插入自己的好友列表
		friend = new Friend();
		friend.setHost_email(account.getEmail());
		friend.setGuest_email(guest_email);
		friend.setMsg(msg);
		friend.setStatue("0");
		friend.setTable("t_friend");
		friend.setTime(DateUtil.currentTime());
		result = friendService.insertFriend(friend);

		// 将对方上的好友申请状态设为0
		friend.setHost_email(guest_email);
		friend.setGuest_email(account.getEmail());
		friend.setMsg(request.getParameter("applyGroup"));
		result = friendService.insertFriend(friend);

		// 响应
		getApply(request, response);
	}

	// 申请好友
	private void applyFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		friend = new Friend();
		friend.setHost_email(account.getEmail());
		friend.setGuest_email(guest_email);
		friend.setMsg(msg);
		friend.setStatue("1");
		friend.setTable("t_friend");
		friend.setTime(DateUtil.currentTime());

		result = friendService.insertFriend(friend);
		if (result.getResult().equals("操作成功")) {
			response.getWriter().write("申请成功，请等待好友通过");
		} else {
			response.getWriter().write("申请失败");
		}
	}

}
