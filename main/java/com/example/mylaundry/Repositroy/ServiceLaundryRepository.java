package com.example.mylaundry.Repositroy;

import com.example.mylaundry.Model.ServiceLaundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceLaundryRepository extends JpaRepository<ServiceLaundry,Integer> {
    ServiceLaundry findServiceLaundryById(Integer id);
    List<ServiceLaundry> findServiceLaundriesByDistrict(String district);
    List<ServiceLaundry> findServiceLaundriesByCategoryBefore(Integer countService);

    //kh
//    ServiceLaundry findServiceLaundriesByhight();
    List<ServiceLaundry> findServiceLaundriesByCategory(String categry);



}