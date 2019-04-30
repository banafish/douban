package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Article1;
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
	public Msg addArticle(Article1 article) {
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

}
