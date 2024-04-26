package com.example.hotel_reservation_system.model;

import java.util.List;

public class Reservation {
    private Hotel hotel;
    private String checkinDate;
    private String checkoutDate;
    private int numberOfRoomsBooked;
    private List<Guest> guests;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getNumberOfRoomsBooked() {
        return numberOfRoomsBooked;
    }

    public void setNumberOfRoomsBooked(int numberOfRoomsBooked) {
        this.numberOfRoomsBooked = numberOfRoomsBooked;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public Reservation(Hotel hotel, String checkinDate, String checkoutDate, int numberOfRoomsBooked, List<Guest> guests) {
        this.hotel = hotel;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.numberOfRoomsBooked = numberOfRoomsBooked;
        this.guests = guests;
    }
}
