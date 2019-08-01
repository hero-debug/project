package com.neu.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.neu.entity.Login;

public class LoginDaoImpl implements LoginDao {

	DBUtils db = new DBUtils();
	
	@Override
	public Login login(String username,String password) throws Exception {
		String sql = "select * from login where username=? and password=?";
		Connection connection = db.getConnection();
		ResultSet rs = db.executeQuery(connection, sql, username,password);
		Login l = null;
		if(rs.next()) {
			l = new Login(username,password);
		}
		return l;
	}

}
