CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE IF NOT EXISTS books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(80),
    quantity INT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120),
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS issued_books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    student_id INT NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ISSUED',
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (student_id) REFERENCES students(id)
);

INSERT INTO books(title, author, category, quantity)
SELECT 'Java Programming', 'Herbert Schildt', 'Programming', 5
WHERE NOT EXISTS (SELECT 1 FROM books);

INSERT INTO books(title, author, category, quantity)
SELECT 'Database System Concepts', 'Korth', 'Database', 3
WHERE NOT EXISTS (SELECT 1 FROM books WHERE title='Database System Concepts');

INSERT INTO students(name,email,phone)
SELECT 'Demo Student', 'student@example.com', '9876543210'
WHERE NOT EXISTS (SELECT 1 FROM students);