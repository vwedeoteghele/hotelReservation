import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Scanner;

public class MainMenu {
    HotelResource hotelResource = HotelResource.getSingleton();
    Scanner scanner = new Scanner(System.in);
    public MainMenu() {
        menu();
    }

    private void menu() {
        System.out.println("Select a number");
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
    }

    private void adminMenu() {

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

    private void reserveRoom() {
        //email, room, checkIn, checkOut
        //find all available rooms
        //print out all available rooms
        //input email, room number , checkIn and checkOut
        //reserve the room
        //print customer reservations
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        Collection<IRoom> findRooms = hotelResource.findARoom();

    }
}
