package com.student;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Studentcontroller")
public class Studentcontroller extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    Connection conn;

    public void init() throws ServletException {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        String rollParam = request.getParameter("roll");
        String nameFilter = request.getParameter("search");

        try {
            if ("delete".equals(action) && rollParam != null) {
                int roll = Integer.parseInt(rollParam);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE roll=?");
                ps.setInt(1, roll);
                ps.executeUpdate();
            } else if ("update".equals(action) && rollParam != null) {
                // Update form submission
                int roll = Integer.parseInt(rollParam);
                String name = request.getParameter("name");
                String cls = request.getParameter("class");
                PreparedStatement ps = conn.prepareStatement("UPDATE students SET name=?, class=? WHERE roll=?");
                ps.setString(1, name);
                ps.setString(2, cls);
                ps.setInt(3, roll);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Records</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: #f4f6f9; margin: 0; padding: 20px; }");
        out.println("h2 { text-align: center; color: #333; }");
        out.println("table { width: 70%; margin: 20px auto; border-collapse: collapse; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }");
        out.println("th, td { padding: 12px; text-align: center; border-bottom: 1px solid #ddd; }");
        out.println("th { background-color: #007bff; color: white; }");
        out.println("tr:hover { background-color: #f1f1f1; }");
        out.println("a.button { padding: 6px 12px; background: #007bff; color: white; text-decoration: none; border-radius: 4px; }");
        out.println("a.delete { background: red; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Student List</h2>");

        //Search Form
        out.println("<form method='get' action='Studentcontroller' style='text-align:center;'>");
        out.println("Search by name: <input type='text' name='search' value='" + (nameFilter != null ? nameFilter : "") + "'/>");
        out.println("<input type='submit' value='Search'/>");
        out.println("</form>");

        try {
            String sql = "SELECT * FROM students";
            if (nameFilter != null && !nameFilter.trim().isEmpty()) {
                sql += " WHERE name LIKE ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            if (nameFilter != null && !nameFilter.trim().isEmpty()) {
                ps.setString(1, "%" + nameFilter + "%");
            }

            ResultSet rs = ps.executeQuery();

            out.println("<table>");
            out.println("<tr><th>Roll</th><th>Name</th><th>Class</th><th>Actions</th></tr>");
            while (rs.next()) {
                int roll = rs.getInt("roll");
                String name = rs.getString("name");
                String cls = rs.getString("class");

                out.println("<tr>");
                out.println("<td>" + roll + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + cls + "</td>");
                out.println("<td>"
                        + "<a class='button' href='Studentcontroller?action=edit&roll=" + roll + "'>Edit</a> "
                        + "<a class='button delete' href='Studentcontroller?action=delete&roll=" + roll + "' onclick=\"return confirm('Delete this student?');\">Delete</a>"
                        + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p style='color:red; text-align:center;'>Error fetching student records!</p>");
        }

        //Edit Form
        if ("edit".equals(action) && rollParam != null) {
            try {
                int roll = Integer.parseInt(rollParam);
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE roll=?");
                ps.setInt(1, roll);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    out.println("<h3 style='text-align:center;'>Update Student</h3>");
                    out.println("<form method='get' action='Studentcontroller' style='text-align:center;'>");
                    out.println("<input type='hidden' name='action' value='update'/>");
                    out.println("<input type='hidden' name='roll' value='" + roll + "'/>");
                    out.println("Name: <input type='text' name='name' value='" + rs.getString("name") + "' required/><br/><br/>");
                    out.println("Class: <input type='text' name='class' value='" + rs.getString("class") + "' required/><br/><br/>");
                    out.println("<input type='submit' value='Update'/>");
                    out.println("</form>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println("<br/><a href='home.jsp' style='display:block; width:150px; margin:20px auto; text-align:center; padding:10px; background:#4CAF50; color:white; text-decoration:none; border-radius:5px;'>Go Back</a>");
        out.println("</body></html>");
    }
}
