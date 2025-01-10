package com.workintech.s18d4.service;

import com.workintech.s18d4.repository.AddressRepository;
import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.exceptions.EntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(long id) {
        Optional<Address> address=addressRepository.findById(id);
        if(address.isPresent()){
            return address.get();
        }else {
            throw new EntityException(id+": sine sahip address bulunamadı!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address delete(long id) {
        Address address=findById(id);
        addressRepository.delete(address);
        return address;
    }
}
