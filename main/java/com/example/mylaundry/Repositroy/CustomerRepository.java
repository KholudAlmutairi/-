package com.example.mylaundry.Repositroy;

import com.example.mylaundry.Model.Customer;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomersById(Integer id);
}
