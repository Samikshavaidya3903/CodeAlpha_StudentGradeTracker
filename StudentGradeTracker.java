import java.util.ArrayList;   // ArrayList is like a resizable list to store students
import java.util.Scanner;     // Scanner is used to take input from the user

//  This class represents ONE student

class Student {
    String name;    // student's name
    double grade;   // student's grade (marks)

    // Constructor: runs automatically when we create a new Student
    Student(String name, double grade) {
        this.name  = name;   // 'this.name' means the field of this class
        this.grade = grade;
    }
}

//  Main class — program starts here

public class StudentGradeTracker {

    // ArrayList to hold all Student objects
    static ArrayList<Student> students = new ArrayList<>();

    // Scanner to read input from keyboard
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       STUDENT GRADE TRACKER            ");
        System.out.println("========================================");

        int choice;  // stores the menu option chosen by user

        // keep showing menu until user chooses to exit
        do {
            printMenu();                        // show options
            choice = sc.nextInt();              // read user's choice
            sc.nextLine();                      // clear leftover newline from input

            switch (choice) {
                case 1: addStudent();    break;
                case 2: viewAll();       break;
                case 3: showSummary();   break;
                case 4: deleteStudent(); break;
                case 5: System.out.println("\nGoodbye! 👋"); break;
                default: System.out.println("❌ Invalid option. Try again.");
            }

        } while (choice != 5);  // loop until user picks 5 (Exit)
    }

    //  Show the menu
    static void printMenu() {
        System.out.println("\n--------- MENU ---------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Show Summary (Avg / Highest / Lowest)");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    //  Add a new student
    static void addStudent() {
        System.out.print("\nEnter student name  : ");
        String name = sc.nextLine();   // read full name (including spaces)

        System.out.print("Enter student grade : ");
        double grade = sc.nextDouble();
        sc.nextLine();

        // validate grade range
        if (grade < 0 || grade > 100) {
            System.out.println("❌ Grade must be between 0 and 100.");
            return;  // stop this method here
        }

        students.add(new Student(name, grade));  // create Student and add to list
        System.out.println("✅ Student added successfully!");
    }

    //  View all students
    static void viewAll() {
        if (students.isEmpty()) {           // check if list is empty
            System.out.println("\n⚠️  No students added yet.");
            return;
        }

        System.out.println("\n--- ALL STUDENTS ---");
        System.out.printf("%-5s %-20s %-10s%n", "No.", "Name", "Grade");
        System.out.println("------------------------------------");

        // loop through every student
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);   // get student at position i
            System.out.printf("%-5d %-20s %-10.2f%n", (i + 1), s.name, s.grade);
            // %-5d  = left-align number in 5 chars
            // %-20s = left-align string in 20 chars
            // .2f   = show 2 decimal places
        }
    }

    // ── Show average, highest, lowest ─────────
    static void showSummary() {
        if (students.isEmpty()) {
            System.out.println("\n⚠️  No students to summarize.");
            return;
        }

        double total   = 0;
        double highest = students.get(0).grade;   // start with first student's grade
        double lowest  = students.get(0).grade;
        String topStudent = students.get(0).name;
        String lowStudent = students.get(0).name;

        // go through each student
        for (Student s : students) {
            total += s.grade;   // add grade to total

            if (s.grade > highest) {   // found a new highest
                highest    = s.grade;
                topStudent = s.name;
            }
            if (s.grade < lowest) {    // found a new lowest
                lowest    = s.grade;
                lowStudent = s.name;
            }
        }

        double average = total / students.size();  // calculate average

        System.out.println("\n======= SUMMARY REPORT =======");
        System.out.printf("Total Students : %d%n",    students.size());
        System.out.printf("Average Grade  : %.2f%n",  average);
        System.out.printf("Highest Grade  : %.2f  (%s)%n", highest, topStudent);
        System.out.printf("Lowest  Grade  : %.2f  (%s)%n", lowest,  lowStudent);
        System.out.println("===============================");
    }

    // ── Delete a student ───────────────────────
    static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("\n⚠️  No students to delete.");
            return;
        }

        viewAll();  // show list first so user knows the numbers
        System.out.print("\nEnter student number to delete: ");
        int num = sc.nextInt();
        sc.nextLine();

        // check if number is valid
        if (num < 1 || num > students.size()) {
            System.out.println("❌ Invalid number.");
            return;
        }

        String name = students.get(num - 1).name;   // get name before deleting
        students.remove(num - 1);                    // remove from list (index = num-1)
        System.out.println("✅ " + name + " removed successfully!");
    }
}