<%@ page import="java.sql.*" %><%@ page import="com.library.util.DBConnection" %>
<!DOCTYPE html><html><head><title>Return Book</title><link rel="stylesheet" href="style.css"></head>
<body><div class="container"><a href="index.jsp">← Dashboard</a><h1>Return Book</h1>
<% if(request.getParameter("error")!=null){ %><div class="error"><%=request.getParameter("error")%></div><% } %>
<form method="post" action="return" class="form vertical">
<label>Select Issued Book</label><select name="issueId" required>
<% try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(
"SELECT i.id,b.title,s.name,i.issue_date FROM issued_books i JOIN books b ON i.book_id=b.id JOIN students s ON i.student_id=s.id WHERE i.status='ISSUED' ORDER BY i.id DESC"); ResultSet r=p.executeQuery()){while(r.next()){%>
<option value="<%=r.getInt(1)%>">#<%=r.getInt(1)%> - <%=r.getString(2)%> / <%=r.getString(3)%> / <%=r.getDate(4)%></option>
<%}} %></select><button>Return Book</button></form></div></body></html>