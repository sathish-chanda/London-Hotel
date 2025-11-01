package com.satish.lil.London_hotel.data.repository;

import com.satish.lil.London_hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest,Long> {
       Optional<Guest> findByFirstNameAndLastNameIgnoreCase(String firstName,String lastName);
}
