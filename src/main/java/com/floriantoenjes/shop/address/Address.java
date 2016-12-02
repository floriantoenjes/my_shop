package com.floriantoenjes.shop.address;

import com.floriantoenjes.shop.core.BaseEntity;
import com.floriantoenjes.shop.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Address extends BaseEntity {
    private String name;
    private String street;
    private String number;
    private String city;
    private String zip;
    private boolean defaultAddress;

    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
