<%@ page import="java.util.*" %>
<!DOCTYPE html><html><head><title>Students</title><link rel="stylesheet" href="style.css"></head>
<body><div class="container"><a href="index.jsp">← Dashboard</a><h1>Student Management</h1>
<form method="post" action="students" class="form">
<input name="name" placeholder="Student Name" required>
<input name="email" type="email" placeholder="Email">
<input name="phone" placeholder="Phone">
<button>Add Student</button></form>
<table><tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Action</th></tr>
<% List<Map<String,Object>> students=(List<Map<String,Object>>)request.getAttribute("students");
for(Map<String,Object> s:students){ %>
<tr><td><%=s.get("id")%></td><td><%=s.get("name")%></td><td><%=s.get("email")%></td><td><%=s.get("phone")%></td>
<td><a class="danger" href="students?action=delete&id=<%=s.get("id")%>" onclick="return confirm('Delete this student?')">Delete</a></td></tr>
<% } %></table></div></body></html>