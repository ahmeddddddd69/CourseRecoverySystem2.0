package crs.controller;

import crs.model.Course;
import crs.model.Grade;
import crs.model.Student;

import crs.util.StudentDataHelper;
import crs.util.CourseDataHelper;
import crs.util.GradeDataHelper;
import crs.util.CgpaHelper;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentController {

    // ==============================================================
    //  CHECK ELIGIBILITY
    // ==============================================================
    public boolean isEligible(Student student) {

        if (student == null) return false;

        ArrayList<Course> allCourses =
                CourseDataHelper.loadCourses("data/course_assessment_information.csv");
        ArrayList<Grade> allGrades =
                GradeDataHelper.loadGrades("data/student_course_grades.csv");

        if (allCourses == null || allCourses.isEmpty()) return false;
        if (allGrades == null || allGrades.isEmpty()) return false;

        double cgpa = CgpaHelper.calculateCgpa(student.getStudentId(), allGrades, allCourses);
        int failedCourses = countFailedCourses(student.getStudentId(), allGrades);

        return cgpa >= 2.0 && failedCourses <= 3;
    }

    // ==============================================================
    //  GET STUDENTS NOT ELIGIBLE
    // ==============================================================
    public List<Student> getStudentsNotEligible() {

        List<Student> result = new ArrayList<>();
        ArrayList<Student> allStudents =
                StudentDataHelper.loadStudents("student_information.csv"); // StudentDataHelper already adds "data/"

        for (Student s : allStudents) {
            if (!isEligible(s)) {
                result.add(s);
            }
        }

        return result;
    }

    // ==============================================================
    //  ENROLL IF ELIGIBLE
    // ==============================================================
    public boolean enrollIfEligible(Student student) {

        if (!isEligible(student)) return false;

        System.out.println("[SUCCESS] Student " + student.getStudentId() + " enrolled.");
        return true;
    }

    // ==============================================================
    //  COUNT FAILED COURSES (PRIVATE)
    // ==============================================================
    private int countFailedCourses(String studentId, ArrayList<Grade> allGrades) {

        int failed = 0;

        for (Grade g : allGrades) {
            if (g.getStudentId().equals(studentId) && g.getGradePoint() < 2.0) {
                failed++;
            }
        }

        return failed;
    }

    // ==============================================================
    //  PUBLIC METHOD FOR UI TO CALL
    // ==============================================================
    public int getFailedCount(String studentId) {
        ArrayList<Grade> allGrades =
                GradeDataHelper.loadGrades("data/student_course_grades.csv");
        return countFailedCourses(studentId, allGrades);
    }
}
