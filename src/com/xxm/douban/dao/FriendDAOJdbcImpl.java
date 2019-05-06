package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.util.DbUtil;

public class FriendDAOJdbcImpl implements FriendDAO{
	private DataSource dataSource;

	private Connection con = null;

	private PreparedStatement stmt = null;

	public FriendDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	//设置关注
	@Override
	public Msg setFollow(String user_email, String follow_email) {
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO t_follow (user_email, follow_email) "
					+ "VALUES (?, ?)  "
					+ "ON DUPLICATE KEY UPDATE user_email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user_email);
			stmt.setString(2, follow_email);
			stmt.setString(3, user_email);
			
			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("关注成功", null);
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
		return new Msg("关注失败", null);
	}

}
