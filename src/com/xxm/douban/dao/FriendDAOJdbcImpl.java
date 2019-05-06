package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.entity.Article;
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

	//获得关注的人
	@Override
	public Msg getFollow(String currentPage, String user_email) {
		try {
			Account account = null;
			List<Account> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "select t_account.* from t_follow, t_account where t_follow.user_email = ? "
					+ "and t_follow.follow_email = t_account.email limit ?, 6";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user_email);
			stmt.setInt(2, (Integer.valueOf(currentPage) - 1) * 6);// 每页6条记录
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				account = new Account();
				account.setEmail(rs.getString("email"));
				account.setSign(rs.getString("sign"));
				account.setAvatar(rs.getString("avatar"));
				account.setName(rs.getString("name"));
				list.add(account);
			}
			if (list.isEmpty()) {
				return new Msg("无关注的人", null);
			} else {
				return new Msg("获得关注的人成功", list);
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
		return new Msg("获得关注的人失败", null);
	}

	//取消关注
	@Override
	public Msg cancelFollow(String user_email, String follow_email) {
		try {
			con = dataSource.getConnection();
			String sql = "delete from t_follow where user_email = ? and follow_email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user_email);
			stmt.setString(2, follow_email);

			// 判断执行删除语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("取消关注成功", null);
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
		return new Msg("取消关注失败", null);
	}

}
