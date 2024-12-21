package com.codegnan.studentcrud.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.codegnan.studentcrud.dao.CandidateDAO;
import com.codegnan.studentcrud.model.Candidate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class CandidateServlet extends HttpServlet {
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

		List<Candidate> listCandidates;
		try {
			listCandidates = candidateDAO.selectAllCandidates();
			request.setAttribute("listCandidate", listCandidates);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("candidateslist.jsp");
		dispatcher.forward(request, response);
	}

}