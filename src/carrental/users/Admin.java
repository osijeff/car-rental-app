package carrental.users;

public class Admin extends User {

    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public void accessDashboard() {
        System.out.println("Admin Panel accessed by " + name);
    }

    public void addCar() {
        System.out.println(name + " is adding a new car to the fleet...");
    }
}