package crs.model;

public class AcademicOfficer extends User {

    public AcademicOfficer(String username, String password,
                           String lastLoginBinary, String lastLogoutBinary) {

        super(username, password, "AcademicOfficer",
              lastLoginBinary, lastLogoutBinary);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Academic Officer");
    }
}
