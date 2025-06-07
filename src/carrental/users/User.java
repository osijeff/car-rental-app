package carrental.users;

public abstract class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String password;
    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updateProfile(String newEmail) {
        this.email = newEmail;
    }

    public abstract void accessDashboard();

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { // ✅ Add this method
        return password;
    }

    public boolean checkPassword(String input){
        return this.password.equals(input);
    }
}
