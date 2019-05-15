package com.revature.services;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.daos.LoginDao;
import com.revature.utils.HttpException;

public class LoginService {
	LoginDao lDao = new LoginDao();
	public User login(Credentials credentials) {
		User toRet = lDao.checkCredByUsername(credentials);
		if(toRet == null)
		{
			throw new HttpException(400);
		}
		return  toRet;
	}

}
