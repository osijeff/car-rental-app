import carrental.users.*;
import carrental.vehicles.*;
import carrental.reservations.*;
import carrental.payment.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Car> cars = new ArrayList<>();
    private static final List<Reservation> reservations = new ArrayList<>();
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Admin> admins = new ArrayList<>();

    public static void main(String[] args) {
        // Sample users
        Customer customer = new Customer(1, "Alice", "alice@example.com", "alice123");
        Admin admin = new Admin(99, "Bob", "bob@admin.com", "admin123");
        customers.add(customer);
        admins.add(admin);

        // Sample cars
        cars.add(new Car(1, "Toyota", "Corolla", 2022, "ABC-123", 45.00));
        cars.add(new Car(2, "Honda", "Civic", 2023, "XYZ-456", 50.00));

        System.out.println("=== Welcome to Car Rental App ===");

        while (true) {
            System.out.println("\n1. Customer Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Register as Customer");
            System.out.println("0. Exit");
            System.out.print("Choose user type: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    User loggedInCustomer = loginUser(customers);
                    if (loggedInCustomer != null) {
                        customerMenu((Customer) loggedInCustomer);
                    } else {
                        System.out.println("Invalid email or password.");
                    }
                }
                case 2 -> {
                    User loggedInAdmin = loginUser(admins);
                    if (loggedInAdmin != null) {
                        adminMenu((Admin) loggedInAdmin);
                    } else {
                        System.out.println("Invalid email or password.");
                    }
                }
                case 3 -> registerCustomer();

                case 0 -> {
                    System.out.println("Thank you for using Car Rental App!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static <T extends User> User loginUser(List<T> userList) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (T user : userList) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void customerMenu(Customer customer) {
        while (true) {
            System.out.println("\n--- Customer Dashboard ---");
            System.out.println("1. View Available Cars");
            System.out.println("2. Make a Reservation");
            System.out.println("0. Back");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewAvailableCars();
                case 2 -> makeReservation(customer);
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. View All Reservations");
            System.out.println("2. Add a Car");
            System.out.println("0. Back");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewAllReservations();
                case 2 -> addCar();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewAvailableCars() {
        System.out.println("\n--- Available Cars ---");
        for (Car car : cars) {
            if (car.getStatus() == CarStatus.AVAILABLE) {
                System.out.println(car.getCarId() + ": " + car.getMake() + " " + car.getModel() + " - $" + car.getRentalPricePerDay());
            }
        }
    }

    private static void makeReservation(Customer customer) {
        viewAvailableCars();
        System.out.print("Enter Car ID to reserve: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        Car selectedCar = null;
        for (Car car : cars) {
            if (car.getCarId() == carId && car.getStatus() == CarStatus.AVAILABLE) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar == null) {
            System.out.println("Invalid or unavailable car.");
            return;
        }

        System.out.print("Start date (yyyy-mm-dd): ");
        LocalDate start = LocalDate.parse(scanner.nextLine());
        System.out.print("End date (yyyy-mm-dd): ");
        LocalDate end = LocalDate.parse(scanner.nextLine());

        Reservation reservation = new Reservation(reservations.size() + 1, customer, selectedCar, start, end);
        reservation.confirm();
        reservations.add(reservation);
        System.out.println("Reservation confirmed!");

        double totalCost = selectedCar.calculateRentalCost((int) (end.toEpochDay() - start.toEpochDay()));
        Payment payment = new Payment(reservation.getReservationId(), reservation, totalCost, "Credit Card", String.valueOf(LocalDate.now()));
        payment.process();
    }

    private static void viewAllReservations() {
        System.out.println("\n--- All Reservations ---");
        for (Reservation r : reservations) {
            System.out.println("Reservation #" + r.getReservationId() + ": " + r.getCustomer().getName() +
                    " - " + r.getCar().getMake() + " " + r.getCar().getModel());
        }
    }

    private static void addCar() {
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("License Plate: ");
        String plate = scanner.nextLine();
        System.out.print("Rental Price per Day: ");
        double price = scanner.nextDouble();

        Car newCar = new Car(cars.size() + 1, make, model, year, plate, price);
        cars.add(newCar);
        System.out.println("Car added to inventory.");
    }

    private static void registerCustomer() {
        System.out.println("\n--- Customer Registration ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        // Check if email is already used
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered. Try logging in.");
                return;
            }
        }

        System.out.print("Create password: ");
        String password = scanner.nextLine();

        int newId = customers.size() + 1;
        Customer newCustomer = new Customer(newId, name, email, password);
        customers.add(newCustomer);

        System.out.println("Registration successful! You can now log in.");
    }

}
