import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MainMenu {
    HotelResource hotelResource = HotelResource.getSingleton();
    Scanner scanner = new Scanner(System.in);
    public MainMenu() throws ParseException {
        menu();
    }

    private void menu() throws ParseException {
        boolean quit = true;
        while(quit) {
            System.out.println("select an option from admin menu(0 - 5): ");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin menu");
            System.out.println("5. Exit");

            int selection = scanner.nextInt();
            switch(selection) {
                case 1:
                    reserveRoom();
                    quit = false;
                    break;
                case 2:
                    myReservations();
                    quit = false;
                    break;
                case 3:
                    createAccount();
                    quit = false;
                    break;
                case 4:
                    adminMenu();
                    quit = false;
                    break;
                case 5:
                    exit();
                    quit = false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }

    }

    private void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private void adminMenu() throws ParseException {
        new AdminMenu();
    }

    private void createAccount() {
        try {
//            scanner.nextLine();
            System.out.println("Email: ");
            String email = scanner.nextLine();
            System.out.println("first name: ");
            String firstName = scanner.nextLine();
            System.out.println("last name: ");
            String lastName = scanner.nextLine();

            hotelResource.createACustomer(email, firstName, lastName);
            Customer customer = hotelResource.getCustomer(email);
            System.out.println(customer);
            menu();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            createAccount();
        }

    }

    private void myReservations() throws ParseException {
        try {
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();
            Collection<Reservation> customerReservations = hotelResource.getCustomersReservation(email);
            if(customerReservations.isEmpty()) {
                System.out.println("You do not have any reservations");
            } else {
                for(Reservation reservation : customerReservations) {
                    System.out.println(reservation);
                }
            }

            menu();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            menu();
        }

    }

    private void reserveRoom() throws ParseException {
        try {
            scanner.nextLine();
            System.out.println("Do you already have an account (Y / N): ");
            String hasAccount = scanner.nextLine().toUpperCase();
            System.out.println(hasAccount.startsWith("N"));
            if(hasAccount.startsWith("N")) {
                createAccount();
            }

            System.out.println("Enter your email: ");
            String email = scanner.nextLine();
            System.out.println("Enter checkIn Date: ");
            String checkInDateInString = scanner.nextLine();
            System.out.println("Enter checkOut Date: ");
            String checkOutDateInString = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

//        String DateInString = "7-Jun-2013";
            Date checkIn = formatter.parse(checkInDateInString);
            Date checkOut = formatter.parse(checkOutDateInString);
            Collection<IRoom> findRooms = hotelResource.findARoom(checkIn, checkOut);

            for(IRoom room : findRooms) {
                System.out.println(room);
            }
            System.out.println("Enter a room Number: ");
            String roomNumber = scanner.nextLine();
            IRoom room = hotelResource.getRoom(roomNumber);
            Reservation reservation = hotelResource.bookARoom(email, room, checkIn, checkOut);
            System.out.println(reservation);
            menu();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            menu();
        }

    }
}
