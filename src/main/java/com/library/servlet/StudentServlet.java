package com.library.servlet;

import com.library.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if ("delete".equals(req.getParameter("action"))) {
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?")) {
                ps.setInt(1, Integer.parseInt(req.getParameter("id")));
                ps.executeUpdate();
            } catch (SQLException e) { throw new ServletException(e); }
            resp.sendRedirect("students");
            return;
        }
        List<Map<String,Object>> students = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM students ORDER BY id DESC");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String,Object> s = new HashMap<>();
                s.put("id", rs.getInt("id"));
                s.put("name", rs.getString("name"));
                s.put("email", rs.getString("email"));
                s.put("phone", rs.getString("phone"));
                students.add(s);
            }
        } catch (SQLException e) { throw new ServletException(e); }
        req.setAttribute("students", students);
        req.getRequestDispatcher("/students.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO students(name,email,phone) VALUES(?,?,?)")) {
            ps.setString(1, req.getParameter("name"));
            ps.setString(2, req.getParameter("email"));
            ps.setString(3, req.getParameter("phone"));
            ps.executeUpdate();
        } catch (SQLException e) { throw new ServletException(e); }
        resp.sendRedirect("students");
    }
}