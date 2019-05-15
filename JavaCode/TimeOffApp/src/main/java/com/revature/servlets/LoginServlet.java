package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.services.LoginService;
import com.revature.utils.HttpException;
/**
 * full service built off of mgoshorns login chat app servlet on github source:https://github.com/190408-usf-nec-java/servlets-chat-app/blob/master/ChatApp/src/main/java/com/revature/servlets/LoginServlet.java
 * @author Thomas
 *
 */
public class LoginServlet extends DefaultServlet {

	LoginService loginService = new LoginService();
	
	
	/**
	 * We're going to need the same CORS headers to avoid CORS issues.
	 * A better way to isolate code so that there is no repetition, would be to use a filter.
	 * 
	 * A filter is a non-terminal, servlet-like object that requests pass through, rather
	 * terminate at.
	 * 
	 * tomcat ---> filter ---> filter ---> filter ---> servlet
	 * <---------------response--------------------------/
	 */
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Headers", "content-type");
		response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		super.service(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();
		System.out.println("post being called");
		Credentials credentials = om.readValue(request.getInputStream(), Credentials.class);
		// 1. Login works fine, a session is created and is communicate to the client in some manner
		// 2. Login fails, some error is received and must be communicated to the client via status code
		//		400 - username/password don't match, or username doesn't exist
		//      500 - Some unhandled exception occurs during processing, servers fault
		User u = null;
		Integer id = null;
		try {
			 u = this.loginService.login(credentials);
			 id = u.getId();
		} catch (HttpException e) {
			response.setStatus(e.getStatus());
			return;
		}
		System.out.println("loggedinto account: " + u);
		String cache = u.getId() +" "+u.getRole()+" "+u.getFirstname() +" "+u.getLastname();
		HttpSession session = request.getSession(true);
		
		session.setAttribute("id", id);	
		response.setStatus(200);
		om.writeValue(response.getOutputStream(), cache);
	}
}