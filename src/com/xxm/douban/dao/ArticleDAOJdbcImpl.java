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
import com.xxm.douban.util.DbUtil;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	private DataSource dataSource;

	private Connection con = null;

	private PreparedStatement stmt = null;

	public ArticleDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//添加文章
	@Override
	public Msg addArticle(Article article) {
		try {
			con = dataSource.getConnection();
			String sql = "insert into t_article(user_email, title, type, content, picture_urls, modify_time) values(?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, article.getUser_email());
			stmt.setString(2, article.getTitle());
			stmt.setString(3, article.getType());
			stmt.setString(4, article.getContent());
			stmt.setString(5, article.getPicture_urls());
			stmt.setString(6, article.getModify_time());

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("发布文章成功", null);
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
		return new Msg("发布文章失败", null);
	}

	//通过当前页数获取文章
	@Override
	public Msg getArticleByPage(String currentPage) {
		try {
			Article article = null;
			List<Article> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "select * from t_article order by hot desc, modify_time desc limit ?, 2";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.valueOf(currentPage) - 1);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				article = new Article();
				article.setId(rs.getString("id"));
				article.setUser_email(rs.getString("user_email"));
				article.setTitle(rs.getString("title"));
				article.setType(rs.getString("type"));
				article.setContent(rs.getString("content"));
				article.setPicture_urls(rs.getString("picture_urls"));
				article.setModify_time(rs.getString("modify_time"));
				article.setHot(rs.getString("hot"));
				list.add(article);
			}
			if (list.isEmpty()) {
				return new Msg("无文章", null);
			} else {
				return new Msg("分页获取文章成功", list);
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
		return new Msg("分页获取文章失败", null);
	}

	//获取文章总数
	@Override
	public Msg getArticleCount() {
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from t_article";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("获取文章总数成功", rs.getInt(1));
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
		return new Msg("获取文章总数失败", null);
	}

}
