<%@ page import="java.sql.*" %><%@ page import="com.library.util.DBConnection" %>
<!DOCTYPE html><html><head><title>Issue Book</title><link rel="stylesheet" href="style.css"></head>
<body><div class="container"><a href="index.jsp">← Dashboard</a><h1>Issue Book</h1>
<% if(request.getParameter("error")!=null){ %><div class="error"><%=request.getParameter("error")%></div><% } %>
<form method="post" action="issue" class="form vertical">
<label>Book</label><select name="bookId" required>
<% try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT id,title,quantity FROM books WHERE quantity>0"); ResultSet r=p.executeQuery()){while(r.next()){%>
<option value="<%=r.getInt(1)%>"><%=r.getString(2)%> (Available: <%=r.getInt(3)%>)</option>
<%}} %></select>
<label>Student</label><select name="studentId" required>
<% try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT id,name FROM students"); ResultSet r=p.executeQuery()){while(r.next()){%>
<option value="<%=r.getInt(1)%>"><%=r.getString(2)%></option>
<%}} %></select>
<button>Issue Book</button></form></div></body></html>