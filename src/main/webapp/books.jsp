<%@ page import="java.util.*" %>
<!DOCTYPE html><html><head><title>Books</title><link rel="stylesheet" href="style.css"></head>
<body><div class="container"><a href="index.jsp">← Dashboard</a><h1>Book Management</h1>
<form method="post" action="books" class="form">
<input name="title" placeholder="Book Title" required>
<input name="author" placeholder="Author" required>
<input name="category" placeholder="Category">
<input name="quantity" type="number" min="1" placeholder="Quantity" required>
<button>Add Book</button></form>
<table><tr><th>ID</th><th>Title</th><th>Author</th><th>Category</th><th>Quantity</th><th>Action</th></tr>
<% List<Map<String,Object>> books=(List<Map<String,Object>>)request.getAttribute("books");
for(Map<String,Object> b:books){ %>
<tr><td><%=b.get("id")%></td><td><%=b.get("title")%></td><td><%=b.get("author")%></td><td><%=b.get("category")%></td><td><%=b.get("quantity")%></td>
<td><a class="danger" href="books?action=delete&id=<%=b.get("id")%>" onclick="return confirm('Delete this book?')">Delete</a></td></tr>
<% } %></table></div></body></html>