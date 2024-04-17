package com.example.mylaundry.Repositroy;

import com.example.mylaundry.Model.Laundry;
import com.example.mylaundry.Model.ServiceLaundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaundryRepository extends JpaRepository<Laundry,Integer> {

    Laundry findLaundryById(Integer id);
    List<Laundry> findLaundriesByDistrict(String district);




}
