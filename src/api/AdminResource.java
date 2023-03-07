package api;

import model.Customer;
import model.IRoom;
import model.RoomType;
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
    public IRoom addSingleRoom(String roomNumber, Double price, int size) {
        RoomType enumeration;
        if(size == 1) {
            enumeration = RoomType.SINGLE;
        } else if(size == 2) {
            enumeration = RoomType.DOUBLE;
        } else {
            throw new IllegalArgumentException("Only accept 1 or 2 as input");
        }
        IRoom room = reservationService.createRoom(roomNumber, price, enumeration);
        return room;
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
