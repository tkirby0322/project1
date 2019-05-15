package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class GrabReimDao {
	/**
	 * 
	 * @param u user to grab all their reimbs for
	 * @return list of all users reimb reqs, null if error
	 * @throws SQLException
	 */
	public List<Reimbursement> grabReimFull(User u) {
		List<Reimbursement> toRet = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement where reimb_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				int amount = rs.getInt("reimb_amount");
				int resolverid = rs.getInt("reimb_resolver");
				String description = rs.getString("reimb_description");
				int statusid = rs.getInt("reimb_status_id");
				int authorid = rs.getInt("reimb_author");
				int typeid = rs.getInt("reimb_type_id");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				Timestamp resolved = rs.getTimestamp("reimb_resolved");
				Reimbursement reimb = new Reimbursement(id, amount, submitted, resolved, description, authorid, resolverid, statusid, typeid);
				toRet.add(reimb);
			}
			return toRet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param u financier grabbing info of everyone but their own reimb
	 * @return a list of all reimb's not users, null if error
	 * @throws SQLException
	 */
	public List<Reimbursement> grabReimInvert(User u) {
		List<Reimbursement> toRet = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement where reimb_author != ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				int amount = rs.getInt("reimb_amount");
				int resolverid = rs.getInt("reimb_resolver");
				String description = rs.getString("reimb_description");
				int statusid = rs.getInt("reimb_status_id");
				int authorid = rs.getInt("reimb_author");
				int typeid = rs.getInt("reimb_type_id");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				Timestamp resolved = rs.getTimestamp("reimb_resolved");
				Reimbursement reimb = new Reimbursement(id, amount, submitted, resolved, description, authorid, resolverid, statusid, typeid);
				toRet.add(reimb);
			}
			return toRet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
}
