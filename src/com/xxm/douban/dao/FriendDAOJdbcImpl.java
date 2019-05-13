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
import com.xxm.douban.entity.Friend;
import com.xxm.douban.util.DateUtil;
import com.xxm.douban.util.DbUtil;

public class FriendDAOJdbcImpl implements FriendDAO {
	private DataSource dataSource;

	private Connection con = null;

	private PreparedStatement stmt = null;

	public FriendDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 设置关注
	@Override
	public Msg setFollow(String user_email, String follow_email) {
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO t_follow (user_email, follow_email) " + "VALUES (?, ?)  "
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

	// 获得关注的人
	@Override
	public Msg getFollow(String currentPage, String user_email) {
		try {
			Friend friend = null;
			List<Friend> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "select t_account.* from t_follow, t_account where t_follow.user_email = ? "
					+ "and t_follow.follow_email = t_account.email limit ?, 6";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user_email);
			stmt.setInt(2, (Integer.valueOf(currentPage) - 1) * 6);// 每页6条记录
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				friend = new Friend();
				friend.setGuest_email(rs.getString("email"));
				friend.setSign(rs.getString("sign"));
				friend.setAvatar(rs.getString("avatar"));
				friend.setName(rs.getString("name"));
				list.add(friend);
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

	// 取消关注
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

	// 跟好友有关的操作
	@Override
	public Msg insertFriend(Friend friend) {
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO " + friend.getTable() + " (host_email, guest_email, msg, time, statue) "
					+ "VALUES (?, ?, ?, ?, ?)  " + "ON DUPLICATE KEY UPDATE msg = ?, statue = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, friend.getHost_email());
			stmt.setString(2, friend.getGuest_email());
			stmt.setString(3, friend.getMsg());
			stmt.setString(4, friend.getTime());
			stmt.setString(5, friend.getStatue());
			stmt.setString(6, friend.getMsg());
			stmt.setString(7, friend.getStatue());

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
		return new Msg("操作失败", null);
	}

	// 取得分组
	@Override
	public Msg getGroup(String host_email) {
		try {
			List<String> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "SELECT DISTINCT msg FROM t_friend WHERE host_email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, host_email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			if (list.isEmpty()) {
				return new Msg("无分组", null);
			} else {
				return new Msg("获得分组成功", list);
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
		return new Msg("获得分组失败", null);
	}

	// 取得朋友申请，朋友列表
	@Override
	public Msg getFriend(String currentPage, String limit) {
		try {
			Friend friend = null;
			List<Friend> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "SELECT t_friend.host_email, t_friend.guest_email, t_friend.msg, t_friend.time, "
					+ "t_account.`name`, t_account.avatar, t_account.sign FROM " + limit + " LIMIT ?, 6";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, (Integer.valueOf(currentPage) - 1) * 6);// 每页6条记录
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				friend = new Friend();
				friend.setHost_email(rs.getString("host_email"));
				friend.setGuest_email(rs.getString("guest_email"));
				friend.setMsg(rs.getString("msg"));
				friend.setTime(rs.getString("time"));
				friend.setName(rs.getString("name"));
				friend.setAvatar(rs.getString("avatar"));
				friend.setSign(rs.getString("sign"));
				list.add(friend);
			}
			if (list.isEmpty()) {
				return new Msg("无信息", null);
			} else {
				return new Msg("操作成功", list);
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
		return new Msg("操作失败", null);
	}

	// 获得朋友，好友申请，黑名单总数
	@Override
	public Msg getFriendCount(String email, String statue, String host_black, String guest_black) {
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from t_friend "
					+ "where host_email = ? and statue = ? and host_black = ? and guest_black = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, statue);
			stmt.setString(3, host_black);
			stmt.setString(4, guest_black);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("获得朋友总数成功", rs.getInt(1));
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
		return new Msg("获得朋友总数失败", null);
	}

	// 获得关注的人总数
	@Override
	public Msg getFollowCount(String email) {
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from t_follow where user_email = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("获得关注的人总数成功", rs.getInt(1));
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
		return new Msg("获得关注的人总数失败", null);
	}

	// 删除好友
	@Override
	public Msg deleteFriend(String host_email, String guest_email) {
		try {
			con = dataSource.getConnection();
			String sql = "delete from t_friend where host_email = ? and guest_email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, host_email);
			stmt.setString(2, guest_email);

			// 判断执行删除语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("删除好友成功", null);
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
		return new Msg("删除好友失败", null);
	}

	// 通过分类获取好友列表
	@Override
	public Msg getFriendByGroup(String currentPage, String host_email, String group) {
		try {
			Friend friend = null;
			List<Friend> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "SELECT t_friend.host_email, t_friend.guest_email, t_friend.msg, t_friend.time, "
					+ "t_account.`name`, t_account.avatar, t_account.sign FROM "
					+ "t_friend, t_account WHERE t_friend.host_email = ? AND t_friend.guest_email = t_account.email "
					+ "AND t_friend.statue = 0 AND t_friend.msg = ? "
					+ "AND t_friend.host_black = 0 AND t_friend.guest_black = 0 "
					+ " ORDER BY t_friend.time DESC LIMIT ?, 6";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, host_email);
			stmt.setString(2, group);
			stmt.setInt(3, (Integer.valueOf(currentPage) - 1) * 6);// 每页6条记录
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				friend = new Friend();
				friend.setHost_email(rs.getString("host_email"));
				friend.setGuest_email(rs.getString("guest_email"));
				friend.setMsg(rs.getString("msg"));
				friend.setTime(rs.getString("time"));
				friend.setName(rs.getString("name"));
				friend.setAvatar(rs.getString("avatar"));
				friend.setSign(rs.getString("sign"));
				list.add(friend);
			}
			if (list.isEmpty()) {
				return new Msg("无信息", null);
			} else {
				return new Msg("通过分类获取好友列表成功", list);
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
		return new Msg("通过分类获取好友列表失败", null);
	}

	// 分类获取好友列表的好友总数
	@Override
	public Msg getFriendGroupCount(String host_email, String group) {
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from t_friend "
					+ "where host_email = ? and statue = 0 and msg = ? and host_black = 0 and guest_black = 0";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, host_email);
			stmt.setString(2, group);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("分类获取好友列表的好友总数成功", rs.getInt(1));
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
		return new Msg("分类获取好友列表的好友总数失败", null);
	}

	// 设置黑名单
	@Override
	public Msg setBlack(Friend friend) {
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO t_friend (host_email, guest_email, host_black,  guest_black) "
					+ "VALUES (?, ?, ?, ?)  " + "ON DUPLICATE KEY UPDATE host_black = ?, guest_black = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, friend.getHost_email());
			stmt.setString(2, friend.getGuest_email());
			stmt.setString(3, friend.getHost_black());
			stmt.setString(4, friend.getGuest_black());
			stmt.setString(5, friend.getHost_black());
			stmt.setString(6, friend.getGuest_black());

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("设置黑名单成功", null);
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
		return new Msg("设置黑名单失败", null);
	}

	// 判断在不在黑名单
	@Override
	public Msg inBlack(String host_email, String guest_email) {
		try {
			con = dataSource.getConnection();
			String sql = "select id from t_friend "
					+ "where host_email = ? and guest_email = ? and (host_black = 1 or guest_black = 1)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, host_email);
			stmt.setString(2, guest_email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("在黑名单", null);
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
		return new Msg("不在黑名单", null);
	}

	// 获取豆邮
	@Override
	public Msg getDouYou(String currentPage, String email) {
		try {
			Friend friend = null;
			List<Friend> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "SELECT t_douyou.id, t_douyou.host_email, t_douyou.guest_email, t_douyou.msg, t_douyou.time, "
					+ "t_account.`name`, t_account.avatar FROM t_douyou, t_account "
					+ "WHERE t_douyou.guest_email = ? AND t_douyou.host_email = t_account.email "
					+ "ORDER BY t_douyou.time DESC LIMIT ?, 6 ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setInt(2, (Integer.valueOf(currentPage) - 1) * 6);// 每页6条记录
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				friend = new Friend();
				friend.setId(rs.getString("id"));
				friend.setHost_email(rs.getString("host_email"));
				friend.setGuest_email(rs.getString("guest_email"));
				friend.setMsg(rs.getString("msg"));
				friend.setTime(rs.getString("time"));
				friend.setName(rs.getString("name"));
				friend.setAvatar(rs.getString("avatar"));
				list.add(friend);
			}
			if (list.isEmpty()) {
				return new Msg("无豆邮", null);
			} else {
				return new Msg("获取豆邮成功", list);
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
		return new Msg("获取豆邮失败", null);
	}

	// 获取豆邮总数
	@Override
	public Msg getDouYouCount(String email) {
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM t_douyou WHERE guest_email = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("获取豆邮总数成功", rs.getInt(1));
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
		return new Msg("获取豆邮总数失败", null);
	}

	// 删除豆邮
	@Override
	public Msg deleteDouYou(String id, String email) {
		try {
			con = dataSource.getConnection();
			String sql = "delete from t_douyou where id = ? and guest_email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, email);

			// 判断执行删除语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("删除豆邮成功", null);
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
		return new Msg("删除豆邮失败", null);
	}

	// 举报
	@Override
	public Msg report(String email, String name) {
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO t_report (report_email, report_name, time) "
					+ "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE time = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, name);
			stmt.setString(3, DateUtil.currentTime());
			stmt.setString(4, DateUtil.currentTime());

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("举报成功", null);
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
		return new Msg("举报失败", null);
	}

	// 获取举报列表
	@Override
	public Msg getReport(String currentPage) {
		try {
			Friend friend = null;
			List<Friend> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = "SELECT * FROM t_report WHERE statue = 0 ORDER BY time DESC LIMIT ?, 6";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, (Integer.valueOf(currentPage) - 1) * 6);// 每页6条记录
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				friend = new Friend();
				friend.setId(rs.getString("id"));
				friend.setGuest_email(rs.getString("report_email"));
				friend.setName(rs.getString("report_name"));
				friend.setTime(rs.getString("time"));
				list.add(friend);
			}
			if (list.isEmpty()) {
				return new Msg("无信息", null);
			} else {
				return new Msg("获取举报列表成功", list);
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
		return new Msg("获取举报列表失败", null);
	}

	// 获得举报总数
	@Override
	public Msg getReportCount() {
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM t_report WHERE statue = 0 ";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("获得举报总数成功", rs.getInt(1));
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
		return new Msg("获得举报总数失败", null);
	}

	// 删除举报
	@Override
	public Msg deleteReport(String email) {
		try {
			con = dataSource.getConnection();
			String sql = "delete from t_report where report_email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);

			// 判断执行删除语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("删除举报成功", null);
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
		return new Msg("删除举报失败", null);
	}

	// 封号
	@Override
	public Msg setReport(String email, String end_time) {
		try {
			con = dataSource.getConnection();
			String sql = "update t_report set statue = ?, end_time = ? where report_email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setString(2, end_time);
			stmt.setString(3, email);

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("设置成功", null);
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
		return new Msg("设置失败", null);
	}

	// 判断是否被封号
	@Override
	public Msg isReport(String email) {
		try {
			con = dataSource.getConnection();
			String sql = "select end_time from t_report where report_email = ? and statue = 1 and end_time > NOW()";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("被封号", rs.getString(1));
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
		return new Msg("不被封号", null);
	}

	// 搜人
	@Override
	public Msg searchPeople(String currentPage, String keyWord) {
		try {
			Friend friend = null;
			List<Friend> list = new ArrayList<>();
			con = dataSource.getConnection();
			String sql = " select * from t_account where email like concat('%',?,'%') or name like concat('%',?,'%') limit ?, 6";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, keyWord);
			stmt.setString(2, keyWord);
			stmt.setInt(3, (Integer.valueOf(currentPage) - 1) * 6);// 每页6条记录
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				friend = new Friend();
				friend.setId(rs.getString("id"));
				friend.setGuest_email(rs.getString("email"));
				friend.setName(rs.getString("name"));
				friend.setAvatar(rs.getString("avatar"));
				friend.setSign(rs.getString("sign"));
				list.add(friend);
			}
			if (list.isEmpty()) {
				return new Msg("无人", null);
			} else {
				return new Msg("搜人成功", list);
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
		return new Msg("搜人失败", null);
	}

	// 搜人的总数
	@Override
	public Msg searchPeopleCount(String keyWord) {
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from t_account where email like concat('%',?,'%') or name like concat('%',?,'%')";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, keyWord);
			stmt.setString(2, keyWord);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Msg("获取搜人的总数成功", rs.getInt(1));
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
		return new Msg("获取搜人的总数失败", null);
	}

}
