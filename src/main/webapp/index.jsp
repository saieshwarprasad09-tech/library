<%@ page import="java.sql.*" %>
<%@ page import="com.library.util.DBConnection" %>
<!DOCTYPE html>
<html><head><title>Library Management</title><link rel="stylesheet" href="style.css"></head>
<body>
<div class="container">
<h1>📚 Library Management System</h1>
<p class="subtitle">FSD-2 Mini Project</p>
<% if(request.getParameter("message") != null){ %>
<div class="success"><%= request.getParameter("message") %></div>
<% } %>
<div class="cards">
<%
int books=0, students=0, issued=0;
try(Connection c=DBConnection.getConnection()){
 try(PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM books"); ResultSet r=p.executeQuery()){r.next();books=r.getInt(1);}
 try(PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM students"); ResultSet r=p.executeQuery()){r.next();students=r.getInt(1);}
 try(PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM issued_books WHERE status='ISSUED'"); ResultSet r=p.executeQuery()){r.next();issued=r.getInt(1);}
}catch(Exception e){}
%>
<div class="card"><h2><%=books%></h2><p>Books</p></div>
<div class="card"><h2><%=students%></h2><p>Students</p></div>
<div class="card"><h2><%=issued%></h2><p>Issued Books</p></div>
</div>
<nav>
<a href="books">📖 Manage Books</a>
<a href="students">👨‍🎓 Manage Students</a>
<a href="issue.jsp">📤 Issue Book</a>
<a href="return.jsp">📥 Return Book</a>
</nav>
</div></body></html>