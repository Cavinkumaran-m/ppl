package com.example.ppl;

public class helperclass {
    String person,description ,problem, phone_num, com_address, com_city, com_postal;
    double latitude_value,longitude_value, com_lat, com_lon;
    int Id,count,fake;
    String status;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        count = count;
    }

    public int getFake() {
        return fake;
    }

    public void setFake(int fake) {
        fake = fake;
    }

    public helperclass() {
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCom_lat() {
        return com_lat;
    }

    public void setCom_lat(double com_lat) {
        this.com_lat = com_lat;
    }

    public double getCom_lon() {
        return com_lon;
    }

    public void setCom_lon(double com_lon) {
        this.com_lon = com_lon;
    }

    public int getId() {
        return Id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public helperclass(int Id, String phone_num, String person, String description, double latitude_value, double longitude_value, String problem, String status,int count,int fake, double com_lat, double com_lon, String com_address, String com_city, String com_postal) {
        this.person = person;
        this.com_address = com_address;
        this.com_city = com_city;
        this.com_postal = com_postal;
        this.description = description;
        this.latitude_value = latitude_value;
        this.longitude_value = longitude_value;
        this.problem=problem;
        this.phone_num = phone_num;
        this.Id=Id;
        this.status = status;
        this.count = count;
        this.com_lat = com_lat;
        this.com_lon = com_lon;
        this.fake = fake;


    }

    public String getCom_address() {
        return com_address;
    }

    public void setCom_address(String com_address) {
        this.com_address = com_address;
    }

    public String getCom_city() {
        return com_city;
    }

    public void setCom_city(String com_city) {
        this.com_city = com_city;
    }

    public String getCom_postal() {
        return com_postal;
    }

    public void setCom_postal(String com_postal) {
        this.com_postal = com_postal;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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
