package com.neu.service;

import com.neu.dao.LoginDao;
import com.neu.dao.LoginDaoImpl;
import com.neu.entity.Login;

public class LoginService {
	
	public boolean login(String username,String password) throws Exception {
		LoginDao l = new LoginDaoImpl();
		Login login = l.login(username, password);
		if(login == null) {
		return false;
		}else {
			return true;
		}
	}

}
