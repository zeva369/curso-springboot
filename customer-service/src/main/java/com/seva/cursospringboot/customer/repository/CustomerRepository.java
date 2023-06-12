package com.seva.cursospringboot.customer.repository;

import com.seva.cursospringboot.customer.repository.entity.Customer;
import com.seva.cursospringboot.customer.repository.entity.Region;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {
        public Customer findByNumberID(String numberID);
        public List<Customer> findByLastName(String lastName);
        public List<Customer> findByRegion(Region region);
}
