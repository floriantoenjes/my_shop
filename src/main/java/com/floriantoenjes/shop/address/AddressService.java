package com.floriantoenjes.shop.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public void save(Address address) {
        addressRepository.save(address);
    }

    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }
}
