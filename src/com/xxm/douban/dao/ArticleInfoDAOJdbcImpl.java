package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Article;
import com.xxm.douban.entity.ArticleInfo;
import com.xxm.douban.entity.Comment;
import com.xxm.douban.entity.Reply;
import com.xxm.douban.util.DbUtil;

public class ArticleInfoDAOJdbcImpl implements ArticleInfoDAO {
	private DataSource dataSource;

	private Connection con = null;

	private PreparedStatement stmt = null;

	public ArticleInfoDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 获取文章全部信息
	@Override
	public Msg getArticleInfo(String id, String user_email) {
		try {
			Article article = null;
			ArticleInfo articleInfo = null;
			con = dataSource.getConnection();
			String sql = "SELECT "
					+ "t_sub.*, t_article_info.good, t_article_info.collect, t_article_info.forword "
					+ "FROM " + "( " + "SELECT t_article.*, t_account.name, t_account.avatar "
					+ "FROM t_article,	t_account "
					+ "WHERE t_article.id = ? AND t_account.email = t_article.author_email " + ") AS t_sub	"
					+ "LEFT OUTER JOIN t_article_info " + "ON t_article_info.article_id = ? "
					+ "AND t_article_info.user_email = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id);
			stmt.setString(3, user_email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				article = new Article();
				articleInfo = new ArticleInfo();

				article.setId(rs.getString("id"));
				article.setAuthor_email(rs.getString("author_email"));
				article.setTitle(rs.getString("title"));
				article.setType(rs.getString("type"));
				article.setContent(rs.getString("content"));
				article.setPicture_urls(rs.getString("picture_urls"));
				article.setModify_time(rs.getString("modify_time"));
				article.setHot(rs.getString("hot"));
				article.setAvatar(rs.getNString("avatar"));
				article.setName(rs.getNString("name"));

				articleInfo.setCollect(rs.getString("collect"));
				articleInfo.setGood(rs.getString("good"));
				articleInfo.setForword(rs.getString("forword"));

				article.setArticleInfo(articleInfo);
				return new Msg("获取文章全部信息成功", article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("获取文章全部信息失败", null);
	}

	// 统计文章点赞等数量
	@Override
	public Msg getArticleInfoDetail(String id) {
		try {
			ArticleInfo articleInfoDetail = new ArticleInfo();
			con = dataSource.getConnection();
			String sql = "SELECT count(good), count(collect), count(forword) "
					+ "FROM (SELECT * FROM t_article_info WHERE article_id = ?) as t_count_sub";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				articleInfoDetail.setGood(rs.getString(1));
				articleInfoDetail.setCollect(rs.getString(2));
				articleInfoDetail.setForword(rs.getString(3));
				return new Msg("统计文章点赞等数量成功", articleInfoDetail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("统计文章点赞等数量失败", null);
	}

	// 点赞收藏转发
	@Override
	public Msg setArticleInfoGCF(String id, String user_email, String method, String value) {
		try {
			con = dataSource.getConnection();
			String sql = "insert into t_article_info (article_id, user_email, " + method + ") values (?, ?, ?)"
					+ "ON DUPLICATE KEY UPDATE " + method + " = ?";// 已确保method为允许范围内的值
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, user_email);
			stmt.setString(3, value);
			stmt.setString(4, value);

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("操作成功", null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("点赞失败", null);
	}

	// 写评论
	@Override
	public Msg setComment(Comment comment) {
		try {
			con = dataSource.getConnection();
			String sql = "insert into t_comment (article_id, user_email, content, time) values (?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, comment.getArticle_id());
			stmt.setString(2, comment.getUser_email());
			stmt.setString(3, comment.getContent());
			stmt.setString(4, comment.getTime());

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("评论成功", null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("评论失败", null);
	}

	// 获取评论
	@Override
	public Msg getComment(String currentPage, String id) {
		try {
			Comment comment = null;
			List<Comment> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "select t_comment.*, t_account.name, t_account.avatar from t_comment, t_account "
					+ "where t_comment.article_id = ? and t_comment.user_email = t_account.email "
					+ "order by good_count desc, time desc limit ?, 6 ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setInt(2, (Integer.valueOf(currentPage) - 1) * 6);// 每页6条记录
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getString("id"));
				comment.setUser_email(rs.getString("user_email"));
				comment.setContent(rs.getString("content"));
				comment.setTime(rs.getString("time"));
				comment.setGood_count(rs.getString("good_count"));
				comment.setName(rs.getString("name"));
				comment.setAvatar(rs.getString("avatar"));
				list.add(comment);
			}
			if (list.isEmpty()) {
				return new Msg("评论", null);
			} else {
				return new Msg("获取评论成功", list);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("获取评论失败", null);
	}

	// 获取评论数量
	@Override
	public Msg getCommentCount(String id) {
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from t_comment where article_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("获取评论数量成功", rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("获取评论数量失败", null);
	}

	// 点赞评论
	@Override
	public Msg setCommentGood(String id, String counts) {
		try {
			con = dataSource.getConnection();
			String sql = "update t_comment set good_count = ? where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, counts);
			stmt.setString(2, id);	

			// 判断执行删除语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("点赞评论成功", null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("点赞评论失败", null);
	}

	//获取回复
	@Override
	public Msg getReply(String id, String reply_email) {
		try {
			Reply reply = null;
			List<Reply> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "select * from t_reply where comment_id = ? and reply_email = ? order by time desc ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, reply_email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				reply = new Reply();
				reply.setId(rs.getString("id"));
				reply.setComment_id(rs.getString("comment_id"));
				reply.setReply_email(rs.getString("reply_email"));
				reply.setReplyer_email(rs.getString("replyer_email"));
				reply.setContent(rs.getString("content"));
				reply.setGood_count(rs.getString("good_count"));
				reply.setTime(rs.getString("time"));
				reply.setReply_name(rs.getString("reply_name"));
				reply.setReplyer_name(rs.getString("replyer_name"));
				list.add(reply);
			}
			if (list.isEmpty()) {
				return new Msg("无回复", null);
			} else {
				return new Msg("获取回复成功", list);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("获取回复失败", null);
	}

	//回复
	@Override
	public Msg setReply(Reply reply) {
		try {
			con = dataSource.getConnection();
			String sql = "insert into t_reply (comment_id, reply_email, replyer_email,  content, time, reply_name, replyer_name) "
					+ "values (?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, reply.getComment_id());
			stmt.setString(2, reply.getReply_email());
			stmt.setString(3, reply.getReplyer_email());
			stmt.setString(4, reply.getContent());
			stmt.setString(5, reply.getTime());
			stmt.setString(6, reply.getReply_name());
			stmt.setString(7, reply.getReplyer_name());

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("回复成功", null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("回复失败", null);
	}

	//赞回复
	@Override
	public Msg setReplyGood(String id, String counts) {
		try {
			con = dataSource.getConnection();
			String sql = "update t_reply set good_count = ? where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, counts);
			stmt.setString(2, id);	

			// 判断执行删除语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("赞回复成功", counts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Msg("赞回复失败", null);
	}

}
