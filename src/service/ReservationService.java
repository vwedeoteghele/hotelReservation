package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    private static final ReservationService SINGLETON = new ReservationService();
    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();
    private final Map<String, IRoom> rooms = new HashMap<>();

    private ReservationService() {}

    public static ReservationService getSingleton() {
        return SINGLETON;
    }
    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }
    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }
    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        Collection<Reservation> customerReservations = getAllCustomersReservation(customer.getEmail());

        if(customerReservations == null) {
            customerReservations = new ArrayList<Reservation>();

        }

        customerReservations.add(reservation);
        reservations.put(customer.getEmail(),customerReservations));

        return reservation;

    }

    private Collection<Reservation> getAllCustomersReservation(String email) {
        return reservations.get(email);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return findAvailableRooms(checkInDate, checkOutDate);
    }

    private Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {
        /**
         * return all rooms that has not been reserved
         *
         * rooms must not intersect on reservations
         * get all reservation and put them inside one container
         * rooms are stored in the rooms collection
         * return rooms that have not been reserved in room collection
         * have a collection to store the rooms that are not available
         * filter out the notAvailable rooms from rooms collections and return the rest
         *loop through all the reservation and see the ones already booked for the date range
         * return  the available ones
         * */
        final Collection<Reservation> allReservation = getAllReservation();
        final Collection<IRoom> roomsNotAvailable = new ArrayList<IRoom>();
        final Collection<IRoom> availableRooms = new ArrayList<>();
        for(Reservation reservation : allReservation) {
            //check if there is an overlap with checkin and checkout dates
            if(overlap(reservation, checkInDate, checkOutDate)) {
                roomsNotAvailable.add(reservation.getRoom());
            }
        }

        for(IRoom room : rooms.values()) {
            for(IRoom roomNotAvailable : roomsNotAvailable) {
                if(room.getRoomNumber() != roomNotAvailable.getRoomNumber()) {
                    availableRooms.add(room);
                }
            }
        }

        return availableRooms;

    }

    private boolean overlap(Reservation reservation, Date checkInDate, Date checkOutDate) {
        return checkInDate.before(reservation.checkOutDate) &&
                checkOutDate.after(reservation.checkInDate);
    }

    private Collection<Reservation> getAllReservation() {
        //initialize an arraylist
        Collection<Reservation> allReservation = new ArrayList<Reservation>();
        for(Collection<Reservation> reservation : reservations.values()) {
            allReservation.addAll(reservation);
        }
        return allReservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.get(customer.getEmail());
    }
    public void printAllReservation() {
        Collection<Reservation> allReservation = getAllReservation();
        if(allReservation.isEmpty()) {
            System.out.println("No reservation found");
        } else {
            for(Reservation reservation: allReservation) {
                System.out.println(reservation);
            }
        }
    }

}