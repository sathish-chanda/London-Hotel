package com.satish.lil.London_hotel.data.repository;

import com.satish.lil.London_hotel.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumberIgnoreCase(String roomNumber);
}
