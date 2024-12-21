package com.codegnan.studentcrud.servlets;

import java.io.IOException;

import com.codegnan.studentcrud.dao.CandidateDAO;
import com.codegnan.studentcrud.model.Candidate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class ShowCandidateEditServlet extends HttpServlet {
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
		int id = Integer.parseInt(request.getParameter("id"));
		Candidate existingCandidate = candidateDAO.selectCandidate(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("updatecandidateform.jsp");
		request.setAttribute("candidate", existingCandidate);
		dispatcher.forward(request, response);

		
	}


	
}