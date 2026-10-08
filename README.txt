LIBRARY MANAGEMENT SYSTEM - FSD 2 MINI PROJECT

Technology:
- Java 17
- JSP
- Servlets (Jakarta)
- MySQL
- Maven
- Apache Tomcat 10.1+
- IntelliJ IDEA

SETUP:
1. Install JDK 17, MySQL and Apache Tomcat 10.1+.
2. Open this folder in IntelliJ IDEA as a Maven project.
3. In MySQL Workbench, run database.sql.
4. Open src/main/java/com/library/util/DBConnection.java
   and change USER/PASSWORD if needed.
5. Configure Tomcat 10.1 as the IntelliJ Run Configuration.
6. Deploy the WAR artifact or the exploded webapp.
7. Open:
   http://localhost:8080/LibraryManagementSystem/

DATABASE:
Database name: library_db
Default MySQL user: root
Change password in DBConnection.java.

FEATURES:
- Dashboard
- Add/View/Delete Books
- Add/View/Delete Students
- Issue Book
- Return Book
- MySQL database
