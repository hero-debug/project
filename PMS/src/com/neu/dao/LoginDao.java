package com.neu.dao;

import com.neu.entity.Login;

public interface LoginDao {
	public Login login(String username,String password) throws Exception;
}
