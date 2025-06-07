package carrental.users;

public class Customer extends User {

    public Customer(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public void accessDashboard() {
        System.out.println("Customer Dashboard accessed by " + getName());
    }

    public void makeReservation() {
        System.out.println(getName() + " is making a reservation...");
    }


//    public void makeReservation() {
//        System.out.println(name + " is making a reservation...");
//    }
}