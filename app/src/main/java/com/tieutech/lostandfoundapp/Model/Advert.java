package com.tieutech.lostandfoundapp.Model;

import java.util.Date;

public class Advert {
    String isItemFound;
    String name;
    String phone;
    String description;
    String date;
    String location;

    public Advert(String isItemFound, String name, String phone, String description, String date, String location) {
        this.isItemFound = isItemFound;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    public Advert(String isItemFound, String description) {
        this.isItemFound = isItemFound;
        this.description = description;
    }

    public String isItemFound() { return isItemFound; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getLocation() { return location; }

    public void setItemFound(String itemFound) { isItemFound = itemFound; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
}
