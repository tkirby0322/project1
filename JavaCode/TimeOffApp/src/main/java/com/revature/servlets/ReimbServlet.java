package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.GrabReimService;
import com.revature.services.GrabStatusService;
import com.revature.services.PostReimService;
import com.revature.services.UpdateReimService;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.daos.GrabStatusDao;

public class ReimbServlet extends DefaultServlet {

	GrabReimService grabReimService = new GrabReimService();
	GrabStatusService grabStatusService = new GrabStatusService();
	PostReimService postReimService = new PostReimService();
	UpdateReimService updateReimService = new UpdateReimService();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Headers", "content-type");
		response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		super.service(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("new reimb incoming");
		ObjectMapper om = new ObjectMapper();
		int typereq = Integer.parseInt(request.getPathInfo().split("/")[1]);
		if(typereq == 1)
		{
			System.out.println("grabbing usersreimbs");
			User u = om.readValue(request.getInputStream(), User.class);
			List<Reimbursement> requests = grabReimService.grabReims(u.getId(), u.getRole() == 2);
			om.writeValue(response.getOutputStream(), requests);
		}
		else if(typereq == 2)
		{
			System.out.println("grabbing usersreimbs");
			User u = om.readValue(request.getInputStream(), User.class);
			List<Reimbursement> requests = grabStatusService.grabReims(u.getId(), 4);
			om.writeValue(response.getOutputStream(), requests);
		}
		else if(typereq == 3)
		{
			System.out.println("resolving reimbs");
			Reimbursement r = om.readValue(request.getInputStream(), Reimbursement.class);
			System.out.println(r);
			updateReimService.resolve(r);
			
		}
		else {
			
			Reimbursement Reimb = om.readValue(request.getInputStream(), Reimbursement.class);
			if (Reimb.getResolverid() == -1) {
				postReimService.saveReim(Reimb);
			} else {
				updateReimService.resolve(Reimb);
			}
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet being called");
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL());
		System.out.println(req.getPathInfo());
		/*
		 * typereq is used to differentiate what kind of request 0 came from users own 1
		 * financer calling for status 1 2 financer calling for status 2 3 financer
		 * calling for status 3 4 financer calling for status 4 5 financer generic
		 * calling
		 */
		int typereq = Integer.parseInt(req.getPathInfo().split("/")[1]);
		HttpSession session = req.getSession();
		int userID = (int) session.getAttribute("id");
		List<Reimbursement> Reimbs = null;
		if (typereq == 0) {
			Reimbs = grabReimService.grabReims(userID, false);
		} else if (typereq == 1) {
			Reimbs = grabStatusService.grabReims(userID, 1);
		} else if (typereq == 2) {
			Reimbs = grabStatusService.grabReims(userID, 2);
		} else if (typereq == 3) {
			Reimbs = grabStatusService.grabReims(userID, 3);
		} else if (typereq == 4) {
			Reimbs = grabStatusService.grabReims(userID, 4);
		} else if (typereq == 5) {
			Reimbs = grabReimService.grabReims(userID, true);
		}

		ObjectMapper objM = new ObjectMapper();
		if (Reimbs != null) {

			objM.writeValue(resp.getOutputStream(), Reimbs);
		} else {
			resp.setStatus(404);
		}

	}
}
