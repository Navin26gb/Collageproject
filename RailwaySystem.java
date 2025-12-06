
import java.util.*;

class Train {
    int trainNo;
    String name;
    String source;
    String destination;
    int seats;

    Train(int trainNo, String name, String source, String destination, int seats) {
        this.trainNo = trainNo;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
    }

    public String toString() {
        return trainNo + "  " + name + "  " + source + " → " + destination + "  Seats: " + seats;
    }
}

class Booking {
    String passengerName;
    int trainNo;
    int seatsBooked;

    Booking(String passengerName, int trainNo, int seatsBooked) {
        this.passengerName = passengerName;
        this.trainNo = trainNo;
        this.seatsBooked = seatsBooked;
    }
}

public class RailwaySystem {

    static ArrayList<Train> trainList = new ArrayList<>();
    static ArrayList<Booking> bookingList = new ArrayList<>();

    public static void addSampleTrains() {
        trainList.add(new Train(101, "Rajdhani Express", "Delhi", "Mumbai", 50));
        trainList.add(new Train(102, "Shatabdi Express", "Patna", "Delhi", 45));
        trainList.add(new Train(103, "Duronto Express", "Mumbai", "Kolkata", 60));
        trainList.add(new Train(104, "Garib Rath", "Ranchi", "Delhi", 70));
    }

    public static Train findTrain(int trainNo) {
        for (Train t : trainList) {
            if (t.trainNo == trainNo) return t;
        }
        return null;
    }

    public static void viewAllTrains() {
        System.out.println("\n--- Available Trains ---");
        for (Train t : trainList) {
            System.out.println(t);
        }
    }

    public static void checkAvailability() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Train Number: ");
        int no = sc.nextInt();

        Train t = findTrain(no);
        if (t == null) {
            System.out.println("Train not found!");
        } else {
            System.out.println("Seats Available: " + t.seats);
        }
    }

    public static void bookTicket() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();

        System.out.print("Enter Train Number: ");
        int no = sc.nextInt();

        Train t = findTrain(no);
        if (t == null) {
            System.out.println("Train does not exist!");
            return;
        }

        System.out.print("How many seats to book? ");
        int seats = sc.nextInt();

        if (seats <= 0 || seats > t.seats) {
            System.out.println("Seats not available!");
            return;
        }

        // Reduce seats
        t.seats -= seats;

        bookingList.add(new Booking(name, no, seats));

        System.out.println("Booking successful! Ticket booked for " + name);
    }

    public static void cancelTicket() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();

        System.out.print("Enter Train Number: ");
        int no = sc.nextInt();

        Booking toCancel = null;
        for (Booking b : bookingList) {
            if (b.passengerName.equalsIgnoreCase(name) && b.trainNo == no) {
                toCancel = b;
                break;
            }
        }

        if (toCancel == null) {
            System.out.println("No booking found!");
            return;
        }

        Train t = findTrain(no);
        t.seats += toCancel.seatsBooked;

        bookingList.remove(toCancel);

        System.out.println("Ticket cancelled successfully!");
    }

    public static void main(String[] args) {
        addSampleTrains();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Railway Enquiry System =====");
            System.out.println("1. View All Trains");
            System.out.println("2. Search/Check Seat Availability");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewAllTrains();
                case 2 -> checkAvailability();
                case 3 -> bookTicket();
                case 4 -> cancelTicket();
                case 5 -> System.out.println("Thank you! Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }
}

