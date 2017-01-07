package com.floriantoenjes.shop.user;

import com.floriantoenjes.shop.address.Address;
import com.floriantoenjes.shop.core.BaseEntity;
import com.floriantoenjes.shop.purchase.Purchase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User extends BaseEntity implements UserDetails{
    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Address> shippingAddresses;

    @ManyToOne(cascade = CascadeType.ALL)
    Role role;

    @OneToMany(mappedBy = "user")
    List<Purchase> purchases;

    public User() {
        shippingAddresses = new ArrayList<>();
    }

    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role.getName());
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<Address> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(List<Address> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
        shippingAddresses.forEach(a -> a.setUser(this));
    }

    public boolean addShippingAddress(Address address) {
        address.setUser(this);
        return shippingAddresses.add(address);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public boolean addPurchase(Purchase purchase) {
        return purchases.add(purchase);
    }
}
