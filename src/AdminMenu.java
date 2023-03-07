import api.AdminResource;
import model.Customer;
import model.IRoom;

import java.text.ParseException;
import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {

    Scanner scanner = new Scanner(System.in);
    AdminResource adminResource = AdminResource.getSingleton();
    AdminMenu() throws ParseException {
        adminMenu();
    }

    private void adminMenu() throws ParseException {
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

    private void seeAllCustomers() throws ParseException {
        try {

            Collection<Customer> allCustomer = adminResource.getAllCustomer();
            if(allCustomer.isEmpty()) {
                System.out.println("There are currently no customers to display");
            } else {
                for(Customer customer : allCustomer) {
                    System.out.println(customer);
                }
            }
            adminMenu();
        }catch(Exception e) {
            System.out.println(e.getMessage());
            adminMenu();
        }

    }

    private void seeAllRooms() throws ParseException {
        System.out.println("got here");
        Collection<IRoom> allRooms = adminResource.getAllRooms();
        try {
            if(allRooms.isEmpty()) {
                System.out.println("There are currently no rooms to display");
            } else {
                for(IRoom room : allRooms) {
                    System.out.println(room);
                }
            }
          adminMenu();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    private void seeAllReservation() throws ParseException {
        try {
            adminResource.displayAllReservation();
            adminMenu();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            adminMenu();
        }

    }

    private void addRoom() throws ParseException {
        try {
            System.out.println("Enter room number: ");
            scanner.nextLine();
            String roomNumber = scanner.nextLine();
            System.out.println(roomNumber);
            System.out.println("Enter the room price: ");
            double price = scanner.nextDouble();
            System.out.println("Enter room enumeration(1. single, 2. double): ");
            int size = scanner.nextInt();
            IRoom room = adminResource.addSingleRoom(roomNumber, price, size);
            System.out.println(room);
            adminMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            addRoom();
        }


    }

    private void returnToMainMenu() throws ParseException {
        new MainMenu();
    }
}
