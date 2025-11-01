package com.satish.lil.London_hotel.data.repository;

import com.satish.lil.London_hotel.data.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByReservationDate(Date resDate);
}
