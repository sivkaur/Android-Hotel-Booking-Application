package com.example.hotel_reservation_system.model;

import java.io.Serializable;

public class Guest implements Serializable {
    private String name;
    private String gender;

    public Guest(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
