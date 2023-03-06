package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private final CustomerService customerService = CustomerService.getSingleton();
    private final ReservationService reservationService = ReservationService.getSingleton();

    private static final AdminResource SINGLETON = new AdminResource();
    private AdminResource() {}
    public static AdminResource getSingleton() {
        return SINGLETON;
    }
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms) {
        for(IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }
    public Collection<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservation() {
        reservationService.printAllReservation();
    }

}
