package com.codegnan.studentcrud.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the current session, or create a new one if it doesn't exist
      System.out.println("Logging out bye bye************************************************************");
    	HttpSession session = request.getSession(false);

        if (session != null) {
        	session.setAttribute("userLoggedIn", false); 
            // Invalidate the session to log the user out
            session.invalidate();
        }

        // Redirect to the login page or any other page as needed
        response.sendRedirect("login.jsp");
    }
}
