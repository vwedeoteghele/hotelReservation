package api;


import jdk.internal.foreign.SystemLookup;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final HotelResource SINGLETON = new HotelResource();


    private final CustomerService customerService = CustomerService.getSingleton();
    private final ReservationService reservationService = ReservationService.getSingleton();
    private HotelResource() {}
    public static HotelResource getSingleton() {
        return SINGLETON;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }
    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }
    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);

    }
    public Collection<Reservation> getCustomersReservation(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}
