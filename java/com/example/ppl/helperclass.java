package com.example.ppl;

public class helperclass {
    String person,address,description ;
    double latitude_value,longitude_value;

    public helperclass() {
    }

    public helperclass(String person, String address, String description, double latitude_value, double longitude_value) {
        this.person = person;
        this.address = address;
        this.description = description;
        this.latitude_value = latitude_value;
        this.longitude_value = longitude_value;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public double getLatitude_value() {
        return latitude_value;
    }

    public void setLatitude_value(double latitude_value) {
        this.latitude_value = latitude_value;
    }

    public double getLongitude_value() {
        return longitude_value;
    }

    public void setLongitude_value(double longitude_value) {
        this.longitude_value = longitude_value;
    }
}
