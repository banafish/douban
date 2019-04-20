package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.xxm.douban.bean.Msg;
import com.xxm.douban.entity.Account;
import com.xxm.douban.util.DbUtil;

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
			stmt.executeUpdate();
			
			return new Msg("注册成功", null);
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

}
