package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Article;
import com.xxm.douban.entity.ArticleInfo;
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
					+ "t_sub.*, t_article_info.good, t_article_info.collect, t_article_info.reply, t_article_info.forword "
					+ "FROM " 
					+ "( " 
					+ "SELECT t_article.*, t_account.name, t_account.avatar "
					+ "FROM t_article,	t_account "
					+ "WHERE t_article.id = ? AND t_account.email = t_article.author_email " 
					+ ") AS t_sub	"
					+ "LEFT OUTER JOIN t_article_info " 
					+ "ON t_article_info.article_id = ? "
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
				articleInfo.setReply(rs.getString("reply"));
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
	
	//统计文章点赞等数量
	@Override
	public Msg getArticleInfoDetail(String id) {
		try {
			ArticleInfo articleInfoDetail = new ArticleInfo();
			con = dataSource.getConnection();
			String sql = "SELECT count(good), count(collect), count(reply), count(forword) "
					+ "FROM (SELECT * FROM t_article_info WHERE article_id = ?) as t_count_sub";
			stmt = con.prepareStatement(sql);
			stmt.setString(1,id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				articleInfoDetail.setGood(rs.getString(1));
				articleInfoDetail.setCollect(rs.getString(2));
				articleInfoDetail.setReply(rs.getString(3));
				articleInfoDetail.setForword(rs.getString(4));
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

	//点赞收藏转发
	@Override
	public Msg setArticleInfoGCF(String id, String user_email, String method, String value) {
		try {
			con = dataSource.getConnection();
			String sql = "insert into t_article_info (article_id, user_email, "
					+ method
					+ ") values (?, ?, ?)"
					+ "ON DUPLICATE KEY UPDATE "
					+ method
					+ " = ?";//已确保method为允许范围内的值
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

}
