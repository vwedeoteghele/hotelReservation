import api.AdminResource;
import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {

    Scanner scanner = new Scanner(System.in);
    AdminResource adminResource = AdminResource.getSingleton();
    AdminMenu() {
        adminMenu();
    }

    private void adminMenu() {
        boolean quit = true;
        
        while(quit) {
            System.out.println("select an option from admin menu(0 - 5): ");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add room");
            System.out.println("5. Return to main menu");
            int selection = scanner.nextInt();
            switch(selection) {
                case 1:
                    seeAllCustomers();
                    quit = false;
                    break;
                case 2:
                    seeAllRooms();
                    quit = false;
                    break;
                case 3:
                    seeAllReservation();
                    quit = false;
                    break;
                case 4:
                    addRoom();
                    quit = false;
                    break;
                case 5:
                    returnToMainMenu();
                    quit = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            } 
        }

    }

    private void seeAllCustomers() {
        Collection<Customer> allCustomer = adminResource.getAllCustomer();
        for(Customer customer : allCustomer) {
            System.out.println(customer);
        }
    }

    private void seeAllRooms() {
        Collection<IRoom> allRooms = adminResource.getAllRooms();
        for(IRoom room : allRooms) {
            System.out.println(room);
        }
        
    }

    private void seeAllReservation() {
        adminResource.displayAllReservation();
        
    }

    private void addRoom() {
        //room number, price, roomtype
        //create room
        //add room to rooms collections
        System.out.println("Enter room number: ");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter the room price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter room enumeration: ");
    }

    private void returnToMainMenu() {
        
    }
}
