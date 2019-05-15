package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.revature.beans.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class PostReimDao {
	/**
	 * 
	 * @param reimbursement to save in database
	 * @return boolean if successful
	 */
	public boolean saveReimb(Reimbursement r) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "insert into ERS_REIMBURSEMENT (REIMB_AMOUNT, REIMB_SUBMITTED,REIMB_DESCRIPTION,REIMB_AUTHOR,REIMB_STATUS_ID,REIMB_TYPE_ID) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getAmount());
			Timestamp t = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(2, t);
			ps.setString(3, r.getDescription());
			ps.setInt(4, r.getAuthorid());
			ps.setInt(5, 4);
			ps.setInt(6, r.getTypeid());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			//TODO
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 * @param r reimbursement thats been resolved
	 * @return boolean if successful
	 */
	public boolean resolveReimb(Reimbursement r) {
		System.out.println("resolving");
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Update ERS_Reimbursement SET REIMB_RESOLVED = ?,REIMB_RESOLVER = ?, REIMB_STATUS_ID = ? where REIMB_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			Timestamp t = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(1, t);
			ps.setInt(2, r.getResolverid());
			ps.setInt(3, r.getStatusid());
			ps.setInt(4, r.getID());
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
