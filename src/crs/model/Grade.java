package crs.model;

public class Grade {

    private String studentId;
    private String courseId;
    private String gradeLetter;
    private double gradePoint;
    private String semester;   // NEW FIELD

    public Grade(String studentId, String courseId, 
                 String gradeLetter, double gradePoint,
                 String semester) {

        this.studentId = studentId;
        this.courseId = courseId;
        this.gradeLetter = gradeLetter;
        this.gradePoint = gradePoint;
        this.semester = semester;
    }

    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public String getGradeLetter() { return gradeLetter; }
    public double getGradePoint() { return gradePoint; }
    public String getSemester() { return semester; }

    // Mapping for recovery plan screen
    public String getCourseCode() { return courseId; }
    public String getComponentName() { return "Overall Grade"; }
}
