package com.revature.beans;

import java.sql.Timestamp;

/**
 * 
 * @author Thomas
 *
 */
public class Reimbursement {
	/*
	 * REIMB_ID  Serial primary key,
	REIMB_AMOUNT Integer not null,
	REIMB_SUBMITTED timestamp not null,
	REIMB_RESOLVED timestamp,
	REIMB_DESCRIPTION VARCHAR(250),
	REIMB_AUTHOR integer references ers_users(ers_users_id),
	REIMB_RESOLVER integer references ers_users(ers_users_id),
	REIMB_STATUS_ID integer references ers_reimbursement_status(reimb_status_id),
	REIMB_TYPE_ID integer references ers_reimbursement_type(reimb_type_id)
	 */
	private int ID;
	private int amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private int authorid;
	private int resolverid;
	private int statusid;
	private int typeid;
	public Reimbursement(int iD, int amount, Timestamp submitted2, Timestamp resolved2, String description,
			int authorid, int resolverid, int statusid, int typeid) {
		super();
		ID = iD;
		this.amount = amount;
		this.submitted = submitted2;
		this.resolved = resolved2;
		this.description = description;
		this.authorid = authorid;
		this.resolverid = resolverid;
		this.statusid = statusid;
		this.typeid = typeid;
	}
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Timestamp getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public int getResolverid() {
		return resolverid;
	}
	public void setResolverid(int resolverid) {
		this.resolverid = resolverid;
	}
	public int getStatusid() {
		return statusid;
	}
	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + amount;
		result = prime * result + authorid;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolverid;
		result = prime * result + statusid;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + typeid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (ID != other.ID)
			return false;
		if (amount != other.amount)
			return false;
		if (authorid != other.authorid)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolverid != other.resolverid)
			return false;
		if (statusid != other.statusid)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (typeid != other.typeid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [ID=" + ID + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", authorid=" + authorid + ", resolverid=" + resolverid
				+ ", statusid=" + statusid + ", typeid=" + typeid + "]";
	}
	
	

}
