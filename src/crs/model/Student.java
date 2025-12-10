package crs.model;

public class Student {

    // Fields (match your CSV)
    public String studentId;
    private String firstName;
    private String lastName;
    private String major;
    private String year;
    private String email;

    // Constructor
    public Student(String studentId, String firstName, String lastName,
                   String major, String year, String email) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.year = year;
        this.email = email;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMajor() {
        return major;
    }

    public String getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Helpful for printing
    @Override
    public String toString() {
        return studentId + " - " + firstName + " " + lastName;
    }
}
