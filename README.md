# Enrollment System (CLI)

A simple **Console-Based Enrollment System** built in Java that allows users to manage and track student enrollment information from the command line.

This project demonstrates core Java programming concepts such as object-oriented design, collections, file handling (if used), and console input handling. It’s ideal as a learning project for practicing Java fundamentals with a real-world inspired CLI (Command-Line Interface) application.

## 🧾 Features

- Add new student records
- List all enrolled students
- Search student by ID or name
- Update student details
- Delete student records  
*(Customize features based on your implementation)*

## 📦 Technologies Used

- Java
- CLI / Console-based interface
- (Optional) File storage or Database if implemented

## 🚀 Getting Started

### Prerequisites

Make sure you have:

- Java JDK (11 or above)
- An IDE (IntelliJ, Eclipse, VS Code) or terminal setup

### 📥 Clone Repository

```bash
git clone https://github.com/loemheng840/enrollment-system.git
🏃 Run the Program
Compile the source code

javac -d bin src/*.java
Run the program

java -cp bin Main
If your main class name or package differs, adjust the commands accordingly.

🧠 How It Works
This application uses a text-based menu to prompt the user for actions such as:

Add Student – Enter student details (name, ID, courses, etc.)

View Students – Display all stored student records

Search Student – Find a student by ID or name

Update Student – Edit an existing student’s details

Delete Student – Remove a student from records

The program loops until the user chooses to exit.

📌 Project Structure
enrollment-system/
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── EnrollmentService.java
│   └── …
├── bin/
├── .gitignore
└── README.md
(Update the folder names based on your actual structure.)

📈 Sample Usage
Welcome to CLI Enrollment System
1) Add Student
2) List Students
3) Search Student
4) Update Student
5) Delete Student
6) Exit
Enter your choice:
🛠 Extend the Project
You could enhance the system with:

Persisting data to a file (CSV, JSON) or database

Adding authentication

Supporting multiple course enrollment per student

Sorting students by name or ID

Exporting reports
