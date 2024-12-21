package com.codegnan.studentcrud.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.codegnan.studentcrud.dao.CandidateDAO;
import com.codegnan.studentcrud.model.Candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class InsertCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidateDAO candidateDAO;

	public void init() {
		candidateDAO = new CandidateDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("contextPath:::::::::getContextPath:::::::::" + request.getContextPath());

		String action = request.getServletPath();

		System.out.println("action:::::::::getServletPath:::::::::" + action);

		System.out.println("Name " + request.getParameter("name"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String qualification = request.getParameter("qualification");
		String state = request.getParameter("state");
		Candidate candidate = new Candidate(name, gender, email, qualification, state);
		try {
			candidateDAO.insertCandidate(candidate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// response.sendRedirect("/crud");
		response.sendRedirect(request.getContextPath() + "/list");
	}

}