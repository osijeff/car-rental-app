package carrental.users;

public abstract class User {
    protected int userId;
    protected String name;
    protected String email;

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
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
}
