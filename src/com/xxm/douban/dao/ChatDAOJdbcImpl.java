package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Friend;
import com.xxm.douban.util.DbUtil;

public class ChatDAOJdbcImpl implements ChatDAO {
	private DataSource dataSource;

	private Connection con = null;

	private PreparedStatement stmt = null;

	public ChatDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 获取密友列表
	@Override
	public Msg getChatFriend(String user_email) {
		try {
			Friend friend = null;
			List<Friend> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "SELECT t_friend.guest_email, t_account.`name`, t_account.avatar FROM t_friend, t_account, "
					+ "( SELECT t_sub1.user_email, t_sub1.follow_email FROM "
					+ "  ( SELECT * FROM t_follow WHERE user_email = ? ) AS t_sub1 , "
					+ "  (SELECT * FROM t_follow WHERE follow_email = ? ) AS t_sub2 "
					+ "   WHERE t_sub1.follow_email = t_sub2.user_email " + ") AS t_sub "
					+ "WHERE t_friend.host_email = t_sub.user_email " + "AND t_sub.follow_email = t_friend.guest_email "
					+ "AND t_account.email = t_sub.follow_email ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user_email);
			stmt.setString(2, user_email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				friend = new Friend();
				friend.setGuest_email(rs.getString("guest_email"));
				friend.setAvatar(rs.getString("avatar"));
				friend.setName(rs.getString("name"));
				list.add(friend);
			}
			if (list.isEmpty()) {
				return new Msg("无密友", null);
			} else {
				return new Msg("获取密友成功", list);
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
		return new Msg("获取密友失败", null);
	}

	// 判断是不是密友
	@Override
	public Msg isChatFriend(String from_email, String to_email) {
		try {
			con = dataSource.getConnection();
			String sql = "SELECT t_friend.id FROM t_friend, t_follow WHERE "
					+ "t_friend.host_email = ? AND t_friend.guest_email = ? "
					+ "AND t_follow.user_email = ? AND t_follow.follow_email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, from_email);
			stmt.setString(2, to_email);
			stmt.setString(3, from_email);
			stmt.setString(4, to_email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Msg("是", null);
			} else {
				return new Msg("不是", null);
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
		return new Msg("判断失败", null);
	}

	// 发信息
	@Override
	public Msg sendChat(String from_email, String to_email, String msg) {
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO t_chat (from_email, to_email, msg) " + "VALUES (?, ?, ?) ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, from_email);
			stmt.setString(2, to_email);
			stmt.setString(3, msg);

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("发送成功", null);
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
		return new Msg("发送失败", null);
	}

	// 读信息
	@Override
	public Msg readChat(String from_email, String to_email) {
		try {
			Map map = null;
			con = dataSource.getConnection();
			String sql = "select id, msg from t_chat where from_email = ? and to_email = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, from_email);
			stmt.setString(2, to_email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				map = new HashMap();
				map.put("id", rs.getString("id"));
				map.put("msg", rs.getString("msg"));
				return new Msg("读信息成功", map);
			} else {
				return new Msg("无信息", null);
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
		return new Msg("读信息失败", null);
	}

	// 删信息
	@Override
	public Msg deleteChat(String id) {
		try {
			con = dataSource.getConnection();
			String sql = "delete from t_chat where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);

			// 判断执行删除语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("删信息成功", null);
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
		return new Msg("删信息失败", null);
	}

}
