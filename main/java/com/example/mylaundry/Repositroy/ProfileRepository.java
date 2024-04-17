package com.example.mylaundry.Repositroy;

import com.example.mylaundry.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findProfileById (Integer id);

}
