package com.revature.services;

import com.revature.beans.User;
import com.revature.daos.GrabStatusDao;
import com.revature.utils.HttpException;

import java.util.List;

import com.revature.beans.Reimbursement;

public class GrabStatusService 
{
	GrabStatusDao GSDao = new GrabStatusDao();
	public List<Reimbursement> grabReims (int userId,int Statusid)
	{
		User gimpedUser = new User(userId, null, null, null, null, null, 0);
		
		List<Reimbursement> toRet;
		toRet = GSDao.grabStatusInvert(gimpedUser, Statusid);
		if(toRet == null)
		{
			throw new HttpException(400);
		}
		else
		{
			return toRet;
		}
			
	}
}
