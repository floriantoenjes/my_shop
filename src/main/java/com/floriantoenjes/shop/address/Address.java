package com.floriantoenjes.shop.address;

import com.floriantoenjes.shop.core.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Address extends BaseEntity {
    private String name;
    private String street;
    private String number;
    private String city;
    private String zip;

    public Address() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
