# Enrollment Management System (CLI)

A full-featured **Command-Line Enrollment Management System** developed in Java.  
This project manages the complete academic workflow including **Students, Teachers, Departments, Majors, Classes, Enrollment, Payments, and Reports**.

It is designed as a practical learning project to apply **Object-Oriented Programming (OOP)** and system design concepts in a real-world university management scenario using a console interface.

---

## ✨ Main Features

### 👨‍🎓 Student Management
- Add, update, delete, and view students  
- Assign students to departments and majors  
- View student enrollment history

### 🏫 Department & Major Management
- Create and manage departments  
- Create and manage majors  
- Link majors to departments  
- View students by department or major

### 👩‍🏫 Teacher Management
- Add, update, and view teachers  
- Assign teachers to departments  
- Assign teachers to classes

### 📚 Class / Course Management
- Create and manage classes  
- Assign teachers to classes  
- Enroll students into classes  
- View class rosters

### 💳 Payment Management
- Record student payments  
- Track paid, unpaid, and partial payments  
- Calculate class and department income  
- View payment history per student

### 📊 Reporting System
- Students by department and major  
- Classes with enrolled student counts  
- Teachers and their assigned classes  
- Payment summaries by class and department  
- System-wide statistics (total students, teachers, classes, revenue, etc.)

### 🧭 Interactive CLI Menu
- Text-based navigation menu  
- Input validation and error handling  
- Loop-based execution until exit

---

## 🛠 Technologies

- Java
- Object-Oriented Programming (OOP)
- Java Collections (List, Map, Set)
- CLI (Console Interface)

---

## 🚀 How to Run

### 1. Clone the Project
```bash
git clone https://github.com/loemheng840/enrollment-system.git
2. Compile
javac -d bin src/**/*.java
3. Run
java -cp bin Main
(Replace Main with your actual main class name if different.)

🧱 Example Project Structure
enrollment-system/
├── src/
│   ├── main/
│   │   ├── Main.java
│   │   ├── student/
│   │   ├── teacher/
│   │   ├── department/
│   │   ├── major/
│   │   ├── course/
│   │   ├── enrollment/
│   │   ├── payment/
│   │   └── report/
├── bin/
└── README.md
📌 Sample Menu
=========== Enrollment System ===========
1. Student Management
2. Teacher Management
3. Department Management
4. Major Management
5. Class Management
6. Enrollment
7. Payment
8. Reports
0. Exit
Choose option:
🎯 Learning Objectives
Practice Java OOP principles
Design layered system architecture
Implement real-world academic workflows
Build a complete management system using CLI
Prepare for upgrading to GUI or Spring Boot REST API

🔮 Future Improvements
Database integration (MySQL / PostgreSQL)
Authentication (Admin, Staff, Student roles)
REST API using Spring Boot
Web or Desktop UI (JavaFX)
Export reports to PDF / Excel

