package com.neu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {
	
	/*private String classname = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=UTF-8";
	private String user = "root";
	private String password = "root";*/
	
	public Connection getConnection () throws ClassNotFoundException, SQLException, NamingException {
		
		 /*Class.forName(classname);
		 Connection connection = DriverManager.getConnection(url, user, password);
		*/ 
		//创建一个上下文对象
		Context context = new InitialContext();
		//在Context中，查找外部资源
		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/mydatasource");
		
		Connection connection = ds.getConnection();
		
		return connection;
	}
	
	public void closeConnection (Connection connection) throws SQLException {
		connection.close();
	}
	
	public int  executeUpdate(String sql,Object...params) throws ClassNotFoundException, SQLException, NamingException {
		
		 Connection connection = getConnection();
		 PreparedStatement statement = connection.prepareStatement(sql);
		 if(params != null) {
			 for(int i = 0 ; i < params.length ; i++) {
			 statement.setObject(i+1,params[i]);
			 }
		 }
		 int n = statement.executeUpdate();
		 statement.close();
		 closeConnection(connection);
		return n;
	}
	
	public ResultSet executeQuery(Connection connection,String sql,Object...params) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql);
		if(params != null) {
			for(int i = 0 ; i < params.length ;i++) {
				statement.setObject(i+1, params[i]);
			}
		}
		ResultSet rs = statement.executeQuery();
		
		return rs;
	}

}
