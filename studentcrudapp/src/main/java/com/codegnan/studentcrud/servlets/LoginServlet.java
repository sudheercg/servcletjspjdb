package com.codegnan.studentcrud.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);

        // JDBC Database Connection
        String jdbcURL = "jdbc:mysql://localhost:3306/sms";
        String dbUser = "root";
        String dbPassword = "password";
        System.out.println(":::::::::::::::::::::::::::In Loginservlet");
        

        try {
        	//com.mysql.cj.jdbc.Driver
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            System.out.println("username: *******"+username);
            System.out.println("username: *******"+password);

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println("**********************************************************************");
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
            	
            	
                // Login successful
            	System.out.println("successful login");
            	request.getSession().setAttribute("userLoggedIn", true);
            	//request.getRequestDispatcher(request.getContextPath()+"/crud").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/list");
            } else {
                // Login failed
            	request.setAttribute("loginFailed", true);
            	request.getRequestDispatcher("login.jsp").forward(request, response);
                //response.sendRedirect("index.jsp");
            }

            result.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
