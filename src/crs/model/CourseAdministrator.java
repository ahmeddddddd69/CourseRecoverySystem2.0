package crs.model;

public class CourseAdministrator extends User {

    public CourseAdministrator(String username, String password,
                              String lastLoginBinary, String lastLogoutBinary) {

        super(username, password, "CourseAdministrator",
              lastLoginBinary, lastLogoutBinary);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Course Administrator");
    }
}
