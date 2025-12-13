package crs.model;

public class Assessment {

    private String studentId;
    private String courseId;
    private String assessmentType;  
    private String gradeLetter;
    private double gradePoint;

    public Assessment(String studentId, String courseId, String assessmentType,
                      String gradeLetter, double gradePoint) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.assessmentType = assessmentType;
        this.gradeLetter = gradeLetter;
        this.gradePoint = gradePoint;
    }

    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public String getAssessmentType() { return assessmentType; }
    public String getGradeLetter() { return gradeLetter; }
    public double getGradePoint() { return gradePoint; }
}
