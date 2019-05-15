package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.daos.LoginDao;
import com.revature.daos.SignupDao;

/**
 * Class: DriverManager -> Older way of getting a Connection object to the
 * database Class: DataSource -> Newer way of getting a Connection object to the
 * database Interface: Connection -> Represents an open connection to the
 * database
 *
 */
public class ConnectionUtil {

	static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	public static Connection getConnection() throws SQLException{
		// JDBC has it's own URL syntax that we will need to use
		// jdbc:postgresql://localhost:5432/postgres

		
		/*
		 * Why should we use environment variables for these details?
		 * 
		 * Code is shared with many people and is pushed to a repository,
		 * and these connection details could be accessed and abused
		 * if they are pushed into any kind of public space. By using
		 * environment variables, these values stay private even if the
		 * code becomes public, preventing our credentials from becoming
		 * public which would violate the security of our database.
		 */
		
		String url = System.getenv("JDBC_URL");
		String username = System.getenv("JDBC_LOGIN");
		String password = System.getenv("JDBC_PASSWORD");
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection();
		SignupDao sDao = new SignupDao();
		User u = new User(-8, "testboi", "password", "test", "er", "tester@gmail.com", 1);
		sDao.saveUser(u);
		//LoginDao lDao = new LoginDao();
		//System.out.println(lDao.checkCredByUsername(new Credentials("Epalp", "sithlord")));
		conn.close();
	}
}
