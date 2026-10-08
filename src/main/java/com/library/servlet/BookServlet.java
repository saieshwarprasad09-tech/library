package com.library.servlet;

import com.library.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE id=?")) {
                ps.setInt(1, Integer.parseInt(req.getParameter("id")));
                ps.executeUpdate();
            } catch (SQLException e) { throw new ServletException(e); }
            resp.sendRedirect("books");
            return;
        }
        List<Map<String,Object>> books = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM books ORDER BY id DESC");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String,Object> b = new HashMap<>();
                b.put("id", rs.getInt("id"));
                b.put("title", rs.getString("title"));
                b.put("author", rs.getString("author"));
                b.put("category", rs.getString("category"));
                b.put("quantity", rs.getInt("quantity"));
                books.add(b);
            }
        } catch (SQLException e) { throw new ServletException(e); }
        req.setAttribute("books", books);
        req.getRequestDispatcher("/books.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO books(title,author,category,quantity) VALUES(?,?,?,?)")) {
            ps.setString(1, req.getParameter("title"));
            ps.setString(2, req.getParameter("author"));
            ps.setString(3, req.getParameter("category"));
            ps.setInt(4, Integer.parseInt(req.getParameter("quantity")));
            ps.executeUpdate();
        } catch (SQLException e) { throw new ServletException(e); }
        resp.sendRedirect("books");
    }
}