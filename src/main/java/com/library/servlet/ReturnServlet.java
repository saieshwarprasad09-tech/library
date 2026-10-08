package com.library.servlet;

import com.library.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/return")
public class ReturnServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/return.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        int issueId = Integer.parseInt(req.getParameter("issueId"));
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            int bookId = -1;
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT book_id FROM issued_books WHERE id=? AND status='ISSUED'")) {
                ps.setInt(1, issueId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) bookId = rs.getInt(1);
                }
            }
            if (bookId == -1) {
                resp.sendRedirect("return.jsp?error=Invalid+issue+ID");
                return;
            }
            try (PreparedStatement ps = con.prepareStatement(
                    "UPDATE issued_books SET return_date=CURDATE(), status='RETURNED' WHERE id=?")) {
                ps.setInt(1, issueId);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement(
                    "UPDATE books SET quantity=quantity+1 WHERE id=?")) {
                ps.setInt(1, bookId);
                ps.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) { throw new ServletException(e); }
        resp.sendRedirect("index.jsp?message=Book+returned+successfully");
    }
}