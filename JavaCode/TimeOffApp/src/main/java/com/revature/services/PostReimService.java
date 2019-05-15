package com.revature.services;


import com.revature.beans.Reimbursement;
import com.revature.daos.PostReimDao;
import com.revature.utils.HttpException;

public class PostReimService {
	/*
	 * ps.setInt(1, r.getAmount());
			Timestamp t = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(2, t);
			ps.setString(3, r.getDescription());
			ps.setInt(4, r.getAuthorid());
			ps.setInt(5, r.getStatusid());
			ps.setInt(6, r.getTypeid());
	 */
	PostReimDao PRDao = new PostReimDao();
	public boolean saveReim(Reimbursement r)
	{
		boolean success = PRDao.saveReimb(r);
		if(!success)
		{
			throw new HttpException(400);
		}
		return true;
	}
}
