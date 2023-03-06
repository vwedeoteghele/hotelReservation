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
    public MainMenu() {
        menu();
    }

    private void menu() throws ParseException {
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
                break;
            case 2:
                myReservations();
                break;
            case 3:
                createAccount();
                break;
            case 4:
                adminMenu();
                break;
            case 5:
                exit();
                break;
        }
    }

    private void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private void adminMenu() {
        new AdminMenu();
    }

    private void createAccount() {
        //requires email, firstname, lastname
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("first name: ");
        String firstName = scanner.nextLine();
        System.out.println("last name: ");
        String lastName = scanner.nextLine();

        hotelResource.createACustomer(email, firstName, lastName);
        Customer customer = hotelResource.getCustomer(email);
        if(customer == null) {
            System.out.println("customer was not created successfully");

        } else {
            System.out.println(customer);
        }
    }

    private void myReservations() {
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        //see all my reservation as a customer
        Collection<Reservation> customerReservations = hotelResource.getCustomersReservation(email);
        for(Reservation reservation : customerReservations) {
            System.out.println(reservation);
        }
    }

    private void reserveRoom() throws ParseException {
        //email, room, checkIn, checkOut
        //find all available rooms
        //print out all available rooms
        //input email, room number , checkIn and checkOut
        //reserve the room
        //print customer reservations
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter checkIn Date: ");
        String checkInDateInString = scanner.nextLine();
        System.out.println("Enter checkOut Date: ");
        String checkOutDateInString = scanner.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

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
    }
}
