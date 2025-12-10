package crs.test;

import crs.controller.*;
import crs.model.*;
import crs.util.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CRSTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===== CRS SYSTEM TEST START =====\n");

        // ----------------------------------------------
        // 1️⃣ USER LOGIN TEST
        // ----------------------------------------------
        System.out.println("\n--- USER LOGIN TEST ---");
        UserController1 userController = new UserController1();

        boolean loginSuccess = userController.validateUser("admin", "admin123");
        System.out.println("Login Success? " + loginSuccess);

        User loggedUser = userController.getUser("admin");
        if (loggedUser != null) {
            System.out.println("Binary Login Timestamp: " + loggedUser.getLastLoginBinary());
        }

        // ----------------------------------------------
        // 2️⃣ LOAD STUDENTS
        // ----------------------------------------------
        System.out.println("\n--- STUDENT LIST ---");

        ArrayList<Student> allStudents = StudentDataHelper.loadStudents("student_information.csv");

        if (allStudents.isEmpty()) {
            System.out.println("[ERROR] No students found!");
            return;
        }

        // Display list of students
        for (Student s : allStudents) {
            System.out.println(s.getStudentId() + " - " + s.getFirstName() + " " + s.getLastName());
        }

        // LET USER TYPE THE STUDENT ID
        System.out.print("\nEnter Student ID to generate report (e.g., S001): ");
        String chosenId = scanner.nextLine().trim();

        Student testStudent = null;

        for (Student s : allStudents) {
            if (s.getStudentId().equalsIgnoreCase(chosenId)) {
                testStudent = s;
                break;
            }
        }

        if (testStudent == null) {
            System.out.println("[ERROR] Student ID not found!");
            return;
        }

        System.out.println("\nSelected Student: " 
                + testStudent.getStudentId() + " - "
                + testStudent.getFirstName() + " " 
                + testStudent.getLastName());

        // ----------------------------------------------
        // 3️⃣ ACADEMIC REPORT TEST
        // ----------------------------------------------
        System.out.println("\n--- ACADEMIC REPORT TEST ---");
        AcademicReportController reportController = new AcademicReportController();

        boolean reportSuccess = reportController.generateReport(testStudent, "Semester 1", 2025);
        System.out.println("Report Generated? " + reportSuccess);

        // ----------------------------------------------
        // 4️⃣ RECOVERY PLAN TEST
        // ----------------------------------------------
        System.out.println("\n--- RECOVERY PLAN TEST ---");
        RecoveryPlanController recoveryController = new RecoveryPlanController();

        boolean created = recoveryController.createRecoveryPlan(testStudent.getStudentId(), "CS101");
        System.out.println("Recovery Plan Created? " + created);

        recoveryController.addTask(testStudent.getStudentId(), "CS101", 1, "Review Notes");
        recoveryController.addTask(testStudent.getStudentId(), "CS101", 2, "Meet Instructor");

        recoveryController.incrementPlanAttempt(testStudent.getStudentId(), "CS101");

        boolean updatedStatus = recoveryController.updatePlanStatus(testStudent.getStudentId(), "CS101", "Ongoing");
        System.out.println("Status Updated? " + updatedStatus);

        // ----------------------------------------------
        // 5️⃣ ELIGIBILITY TEST
        // ----------------------------------------------
        System.out.println("\n--- ELIGIBILITY TEST ---");
        EnrollmentController enrollmentController = new EnrollmentController();

        boolean eligible = enrollmentController.isEligible(testStudent);
        System.out.println("Is Student Eligible? " + eligible);

        System.out.println("\n===== CRS SYSTEM TEST COMPLETE =====\n");
    }
}
