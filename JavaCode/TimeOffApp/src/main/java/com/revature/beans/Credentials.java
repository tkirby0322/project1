package com.revature.beans;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Credentials {
	private String username;
	private String password;
	
	private String hash(String password,String salt)
	{
		String sha256hex = Hashing.sha256()
				  .hashString(password+salt, StandardCharsets.UTF_8)
				  .toString();
		return sha256hex;
	}
	
	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean verify(String hashed,String usalt)
	{
		return hashed.equals(hash(password,usalt));
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Credentials other = (Credentials) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Credentials [username=" + username + ", password=" + password + "]";
	}
	
	
}