package model;

public class Room implements IRoom{

    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room(String roomNumber,Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this. enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        if(this.price <= 0.0) {
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Room number: " + roomNumber + " price: " + price + "room type: " + enumeration;

    }
}
