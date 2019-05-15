package com.revature.services;

import com.revature.beans.User;
import com.revature.daos.GrabReimDao;
import com.revature.utils.HttpException;

import java.util.List;

import com.revature.beans.Reimbursement;

public class GrabReimService 
{
	GrabReimDao GRDao = new GrabReimDao();
	/**
	 * 
	 * @param userId which user to grab reims for
	 * @param financier boolean of whether they are a financier
	 * @return list of desired reimbursements
	 */
	public List<Reimbursement> grabReims (int userId,boolean financier)
	{
		User gimpedUser = new User(userId, null, null, null, null, null, 0);
		
		List<Reimbursement> toRet;
		if(financier)
		{
			toRet = GRDao.grabReimInvert(gimpedUser);
			
		}
		else
		{
			toRet = GRDao.grabReimFull(gimpedUser);
		}
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
