package crs.model;

public abstract class User {

    protected String username;
    protected String password;
    protected String role;

    protected String lastLoginBinary;
    protected String lastLogoutBinary;

    public User(String username, String password, String role,
                String lastLoginBinary, String lastLogoutBinary) {

        this.username = username;
        this.password = password;
        this.role = role;
        this.lastLoginBinary = lastLoginBinary;
        this.lastLogoutBinary = lastLogoutBinary;
    }

    public abstract void displayRole();

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getLastLoginBinary() { return lastLoginBinary; }
    public void setLastLoginBinary(String lastLoginBinary) {
        this.lastLoginBinary = lastLoginBinary;
    }

    public String getLastLogoutBinary() { return lastLogoutBinary; }
    public void setLastLogoutBinary(String lastLogoutBinary) {
        this.lastLogoutBinary = lastLogoutBinary;
    }
}
