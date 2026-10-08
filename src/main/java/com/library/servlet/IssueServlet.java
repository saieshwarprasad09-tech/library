package com.library.servlet;

import com.library.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/issue")
public class IssueServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/issue.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement check = con.prepareStatement(
                    "SELECT quantity FROM books WHERE id=?")) {
                check.setInt(1, bookId);
                try (ResultSet rs = check.executeQuery()) {
                    if (!rs.next() || rs.getInt(1) <= 0) {
                        resp.sendRedirect("issue.jsp?error=Book+not+available");
                        return;
                    }
                }
            }
            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO issued_books(book_id,student_id,issue_date,status) VALUES(?,?,CURDATE(),'ISSUED')")) {
                ps.setInt(1, bookId);
                ps.setInt(2, studentId);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement(
                    "UPDATE books SET quantity=quantity-1 WHERE id=?")) {
                ps.setInt(1, bookId);
                ps.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) { throw new ServletException(e); }
        resp.sendRedirect("index.jsp?message=Book+issued+successfully");
    }
}