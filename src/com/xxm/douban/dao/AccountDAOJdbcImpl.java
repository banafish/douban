package com.xxm.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.xxm.douban.entity.Account;

public class AccountDAOJdbcImpl implements AccountDAO{
	private DataSource dataSource;
	
	private Connection conn = null;
	
	private PreparedStatement stmt = null;
	
	public AccountDAOJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean isUserExisted(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addAccount(Account account) {
		try {
			conn = dataSource.getConnection();
			String sql = "insert into t_account(name, password, email) values(?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getEmail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Account getAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
