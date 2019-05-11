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
import com.xxm.douban.entity.Article;
import com.xxm.douban.entity.ArticleInfo;
import com.xxm.douban.entity.Comment;
import com.xxm.douban.entity.Reply;
import com.xxm.douban.service.ArticleInfoService;
import com.xxm.douban.service.ArticleService;
import com.xxm.douban.service.FriendService;
import com.xxm.douban.util.DateUtil;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/articleInfoServlet")
public class ArticleInfoServlet extends HttpServlet {
	private ArticleInfoService articleInfoService;

	private ArticleService articleService;

	private FriendService friendService;

	private Msg msg;

	private Account account;

	private String id;// 文章id

	private String author_email;

	private String currentPage;// 当前页码

	private int totalCounts;// 数据总数

	private int totalPages;// 总页数

	private List<Comment> comments;// 评论

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		articleInfoService = (ArticleInfoService) getServletContext().getAttribute("articleInfoService");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		// 获取用户属性
		account = (Account) session.getAttribute("account");
		// 获取id
		id = request.getParameter("id");
		String content = request.getParameter("content");
		String method = request.getParameter("method");

		if (method != null && method.equals("comment")) {
			Comment comment = new Comment();
			comment.setArticle_id(id);
			comment.setUser_email(account.getEmail());
			comment.setContent(content);
			comment.setTime(DateUtil.currentTime());

			msg = articleInfoService.setComment(comment);

			response.getWriter().write(msg.getResult());
		}
		
		if (method != null && method.equals("reply")) {
			Reply reply = new Reply();
			reply.setReply_email(request.getParameter("reply_email"));
			reply.setReplyer_email(account.getEmail());
			reply.setComment_id(id);
			reply.setContent(content);
			reply.setTime(DateUtil.currentTime());
			reply.setReply_name(request.getParameter("reply_name"));
			reply.setReplyer_name(account.getName());
			
			msg = articleInfoService.setReply(reply);
			
			if (msg.getResult().equals("回复成功")) {
				response.getWriter().write("回复成功");
			} else {
				response.getWriter().write("回复失败");
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		articleInfoService = (ArticleInfoService) getServletContext().getAttribute("articleInfoService");
		articleService = (ArticleService) getServletContext().getAttribute("articleService");
		friendService = (FriendService) getServletContext().getAttribute("friendService");
		HttpSession session = request.getSession();

		// 获取用户属性
		account = (Account) session.getAttribute("account");

		// 获取文章id
		id = request.getParameter("id");

		// 获取作者email
		author_email = request.getParameter("author_email");

		// 获取当前页数
		currentPage = request.getParameter("p");
		if (currentPage == null) {
			currentPage = "1";// 如果为空默认请求第一页数据
		}

		String method = request.getParameter("method");
		String realMethod = null;// 真实method,防止sql注入

		// 根据不同的method调不同的方法
		if (method == null) {
			getArticleInfo(request, response);
			return;
		}

		// 删除文章
		if (method.equals("delete")) {
			deleteArticle(request, response);
			return;
		}

		// 关注
		if (method.equals("follow")) {
			setFollow(request, response);
			return;
		}

		// 置顶/取消置顶
		if (method.equals("setHot")) {
			setArticleHot(request, response, 1);// 1代表置顶
			return;
		}
		if (method.equals("cancelHot")) {
			setArticleHot(request, response, 0);// 0代表取消置顶
			return;
		}
		// 赞评论
		if (method.equals("goodComment")) {
			goodComment(request, response);// 0代表取消置顶
			return;
		}
		// 赞回复
		if (method.equals("goodReply")) {
			msg = articleInfoService.setReplyGood(id, request.getParameter("count"));
			if (msg.getResult().equals("赞回复成功")) {
				response.getWriter().write((String)msg.getMessage());
			}
			return;
		}

		// 点赞收藏转发
		if (method.equals("good")) {
			realMethod = "good";
		}
		if (method.equals("collect")) {
			realMethod = "collect";
		}
		if (method.equals("forword")) {
			realMethod = "forword";
		}
		setArticleInfoGCF(request, response, realMethod);

	}

	// 赞评论
	private void goodComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		articleInfoService.setCommentGood(request.getParameter("comment_id"), request.getParameter("counts"));
		getArticleInfo(request, response);
	}

	// 设置关注
	private void setFollow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		msg = friendService.setFollow(account.getEmail(), author_email);

		request.setAttribute("msg", msg);

		getArticleInfo(request, response);
	}

	// 删除文章
	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		msg = articleService.deleteArticle(id);

		if (msg.getResult().equals("删除文章成功")) {
			request.setAttribute("msg", msg);
			// 响应
			getArticleInfo(request, response);
		}
	}

	// 点赞收藏转发
	private void setArticleInfoGCF(HttpServletRequest request, HttpServletResponse response, String method)
			throws ServletException, IOException {

		// 点赞收藏转发
		msg = articleInfoService.setArticleInfoGCF(id, account.getEmail(), method, "1");
		request.setAttribute("msg", msg);
		// 响应
		getArticleInfo(request, response);
	}

	// 置顶/取消置顶
	private void setArticleHot(HttpServletRequest request, HttpServletResponse response, int hot)
			throws ServletException, IOException {

		msg = articleService.setArticleHot(id, hot);
		request.setAttribute("msg", msg);
		// 响应
		getArticleInfo(request, response);
	}

	// 获取文章全部信息
	private void getArticleInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取文章全部信息
		String user_email = null;
		if (account != null) {
			user_email = account.getEmail();
		}
		// 文章信息
		msg = articleInfoService.getArticleInfo(id, user_email);
		Article article = (Article) msg.getMessage();
		request.setAttribute("article", article);
		// 用户好友分组信息
		msg = friendService.getGroup(user_email);
		List<String> groups = (List<String>) msg.getMessage();
		request.setAttribute("groups", groups);

		// 统计文章点赞等数量
		msg = articleInfoService.getArticleInfoDetail(id);
		ArticleInfo articleInfoDetail = (ArticleInfo) msg.getMessage();

		// 评论数量
		msg = articleInfoService.getCommentCount(id);
		totalCounts = (int) msg.getMessage();
		totalPages = ((totalCounts % 6 == 0) ? (totalCounts / 6) : (totalCounts / 6 + 1));// 总页数，每页6条

		// 评论信息
		msg = articleInfoService.getComment(currentPage, id);
		comments = (List<Comment>) msg.getMessage();

		request.setAttribute("comments", comments);
		request.setAttribute("target", "articleInfoServlet?id=" + id + "&");
		request.setAttribute("commentCount", totalCounts);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("articleInfoDetail", articleInfoDetail);
		request.getRequestDispatcher("articleInfo.jsp").forward(request, response);
	}

}
