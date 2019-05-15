package com.xxm.douban.util;

import java.sql.*;

/**
 * JDBC工具类
 */
public class DbUtil {
	
	/**
	 * 关闭连接
	 * @param con
	 * @throws Exception
	 */
	public static void close(PreparedStatement st,Connection con)throws SQLException{
		if(st!=null){
			st.close();
			if(con!=null){
				con.close();
			}
		}
	}
}
