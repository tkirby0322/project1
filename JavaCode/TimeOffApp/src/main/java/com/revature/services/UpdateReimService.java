package com.revature.services;

import com.revature.beans.Reimbursement;
import com.revature.daos.PostReimDao;
import com.revature.utils.HttpException;

public class UpdateReimService {
	PostReimDao PRDao = new PostReimDao();
	public boolean resolve(Reimbursement r)
	{
		boolean success = PRDao.resolveReimb(r);
		if(!success)
		{
			throw new HttpException(400);
		}
		return true;
	}
}
