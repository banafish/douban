package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.util.DbUtil;

/**
 * 实现类
 */
public class AccountDAOJdbcImpl implements AccountDAO {
	private DataSource dataSource;

	private Connection con = null;

	private PreparedStatement stmt = null;

	public AccountDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 注册
	@Override
	public Msg register(Account account) {
		try {
			con = dataSource.getConnection();
			String sql = "insert ignore into t_account(name, password, email) values(?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, account.getName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getEmail());

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("注册成功", null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtil.close(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
				return new Msg("注册失败", null);
			}
		}
		return new Msg("用户已存在", null);
	}

	// 登录
	@Override
	public Msg login(Account account) {
		try {
			con = dataSource.getConnection();
			String sql = "select * from t_account where email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, account.getEmail());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (account.getPassword().equals(rs.getString("password"))) {
					account.setName(rs.getString("name"));
					account.setAvatar(rs.getNString("avatar"));
					account.setSign(rs.getNString("sign"));
					account.setPassword(null);
					return new Msg("登录成功", account);
				}
				return new Msg("密码错误", null);
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
		return new Msg("该用户不存在", null);
	}

	//重置密码
	@Override
	public Msg resetPassword(Account account) {
		try {
			con = dataSource.getConnection();
			String sql = "update t_account set password = ? where email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, account.getPassword());
			stmt.setString(2, account.getEmail());

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("重置密码成功", null);
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
		return new Msg("重置密码失败", null);
	}

	//判断是否存在该用户
	@Override
	public Msg isExistAccount(String email) {
		try {
			con = dataSource.getConnection();
			String sql = "select email from t_account where email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {				
				return new Msg("存在", null);
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
		return new Msg("该邮箱未注册", null);
	}

	//添加头像
	@Override
	public Msg addAvatar(String pic, String email) {
		try {
			con = dataSource.getConnection();
			String sql = "update t_account set avatar = ? where email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pic);
			stmt.setString(2, email);

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("上传成功", pic);
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
		return new Msg("上传失败", null);
	}

	//添加签名
	@Override
	public Msg addSign(String sign, String email) {
		try {
			con = dataSource.getConnection();
			String sql = "update t_account set sign = ? where email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sign);
			stmt.setString(2, email);

			// 判断执行插入语句后受影响语句是否大于0
			if (stmt.executeUpdate() > 0) {
				return new Msg("签名成功", sign);
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
		return new Msg("签名失败", null);
	}

	//获得角色
	@Override
	public Msg getRole(String email) {
		try {
			con = dataSource.getConnection();
			String sql = "select email from t_role where email = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {				
				return new Msg("是管理员", null);
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
		return new Msg("", null);
	}

}
