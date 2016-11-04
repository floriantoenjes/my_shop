package com.floriantoenjes.shop.user;

import com.floriantoenjes.shop.core.BaseEntity;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity{
    private String email;

    private String password;
}
