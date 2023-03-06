package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    public Date checkInDate;
    public Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
    }

    public IRoom getRoom() {
        return room;
    }

    public String toString() {
        return "customer: " + customer + " room: " + room + " checkInDate: " + checkInDate + " checkOutDate: " + checkOutDate;
    }
}
