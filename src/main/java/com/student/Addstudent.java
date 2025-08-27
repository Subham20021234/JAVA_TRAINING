package com.student;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Addstudent")
public class Addstudent extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    Connection conn;

    public void init() throws ServletException {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            throw new ServletException("DB Connection failed: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int roll = Integer.parseInt(request.getParameter("roll"));
        String name = request.getParameter("name");
        String className = request.getParameter("className");

        try {
            String sql = "INSERT INTO students (roll, name, class) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roll);
            pstmt.setString(2, name);
            pstmt.setString(3, className);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Student added successfully!');");
                out.println("window.location.href='home.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Failed to add student!');");
                out.println("window.location.href='home.jsp';");
                out.println("</script>");
            }

        } catch (SQLException e) {
             out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: " + e.getMessage().replace("'", "") + "');");
            out.println("window.location.href='home.jsp';");
            out.println("</script>");
        }
    }
}
