package crs.controller;

import crs.util.*;
import crs.controller.*;
import crs.model.*;

import java.util.ArrayList;

public class CRSTest {

    public static void main(String[] args) {

        System.out.println("=============== FILEHELPER TEST ===============");
        testFileHelper();

        System.out.println("\n=============== STUDENT DATA TEST ===============");
        testStudentData();

        System.out.println("\n=============== GRADE + COURSE DATA TEST ===============");
        testGradeAndCourseData();

        System.out.println("\n=============== ENROLLMENT CONTROLLER TEST ===============");
        testEnrollmentController();

        System.out.println("\n=============== ACADEMIC REPORT CONTROLLER TEST ===============");
        testAcademicReportController();

        System.out.println("\n=============== USER CONTROLLER TEST ===============");
        testUserController();

        System.out.println("\n=============== RECOVERY PLAN CONTROLLER TEST ===============");
        testRecoveryPlanController();

        System.out.println("\n=============== ALL TESTS FINISHED ===============");
    }

    private static void testFileHelper() {
        String[] paths = {
            "student_information.csv",
            "src/data/student_course_grades.csv",
            "data/course_assessment_information.csv",
            "data/src/data/student_course_grades.csv"
        };

        for (String p : paths) {
            System.out.println("Input:  " + p);
            System.out.println("Output: " + FileHelper.fixPath(p));
            System.out.println();
        }
    }

    private static void testStudentData() {
        var list = StudentDataHelper.loadStudents("student_information.csv");
        System.out.println("Loaded students: " + list.size());

        for (Student s : list) {
            System.out.println(s.getStudentId() + " - " + s.getFirstName() + " " + s.getLastName());
        }
    }

    private static void testGradeAndCourseData() {
        var grades = GradeDataHelper.loadGrades("student_course_grades.csv");
        var courses = CourseDataHelper.loadCourses("course_assessment_information.csv");

        System.out.println("Grades loaded: " + grades.size());
        System.out.println("Courses loaded: " + courses.size());

        if (!grades.isEmpty()) {
            Grade g = grades.get(0);
            System.out.println("Example grade: " + g.getStudentId() + " " + g.getCourseCode());
        }

        if (!courses.isEmpty()) {
            Course c = courses.get(0);
            System.out.println("Example course: " + c.getCourseCode() + " " + c.getCourseName());
        }
    }

    private static void testEnrollmentController() {
        EnrollmentController ec = new EnrollmentController();

        String testId = "S001"; // CHANGE based on your CSV

        int failed = ec.getFailedCount(testId);
        System.out.println("Failed count for " + testId + ": " + failed);

        // You need a Student object for eligibility test
        Student dummy = new Student(testId, "X", "Y", "Z", "1", "email@mail.com");

        boolean eligible = ec.isEligible(dummy);
        System.out.println("Eligibility for " + testId + ": " + eligible);
    }

    private static void testAcademicReportController() {
    AcademicReportController arc = new AcademicReportController();

    Student dummy = new Student("S001", "X", "Y", "Z", "1", "e@mail.com");

    String output = "data/test_academic_report.pdf";
    int semester = 1;   // you can change this depending on your CSV

    boolean ok = arc.generateReport(dummy, output, semester);

    System.out.println("Academic report generation result: " + ok);
    }

    private static void testUserController() {
        UserController1 uc = new UserController1();

        boolean login = uc.validateUser("admin", "admin123");
        System.out.println("User login test: " + login);
    }

    private static void testRecoveryPlanController() {
        RecoveryPlanController rpc = new RecoveryPlanController();

        rpc.createRecoveryPlan("S001", "SE001");
        rpc.addTask("S001", "SE001", 1, "Study Chapter 1");

        var plans = rpc.getRecoveryPlansByStudent("S001");
        System.out.println("Recovery plans found: " + plans.size());
    }
}

