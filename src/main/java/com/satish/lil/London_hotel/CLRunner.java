package com.satish.lil.London_hotel;

import com.satish.lil.London_hotel.data.entity.Guest;
import com.satish.lil.London_hotel.data.entity.Reservation;
import com.satish.lil.London_hotel.data.entity.Room;
import com.satish.lil.London_hotel.data.repository.GuestRepository;
import com.satish.lil.London_hotel.data.repository.ReservationRepository;
import com.satish.lil.London_hotel.data.repository.RoomRepository;
import com.satish.lil.London_hotel.service.RoomReservationSerice;
import com.satish.lil.London_hotel.service.model.RoomReservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CLRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomReservationSerice roomReservationSerice;
    public CLRunner(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository, RoomReservationSerice roomReservationSerice) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomReservationSerice = roomReservationSerice;
    }


    @Override
    public void run(String... args) throws Exception {
//            System.out.println("********************* ROOMS *******************");
//            List<Room> rooms = this.roomRepository.findAll();
//            Optional<Room> room = this.roomRepository.findByRoomNumberIgnoreCase("p1");
//            System.out.println(room);
//            rooms.forEach(System.out::println);
//
//            System.out.println("********************* Guests *******************");
//            Optional<Guest> guest = this.guestRepository.findByFirstNameAndLastNameIgnoreCase("Tammy","Burke");
//            System.out.println(guest);
//            List<Guest> guests = this.guestRepository.findAll();
//            guests.forEach(System.out::println);
//
//            System.out.println("********************* Reservations *******************");
//            List<Reservation> reservations = this.reservationRepository.findAllByReservationDate(Date.valueOf("2023-08-28"));
//            reservations.forEach(System.out::println);
              List<RoomReservation> reservations =  this.roomReservationSerice.getRoomReservationsForDate("2023-08-28");
              reservations.forEach(System.out::println);
    }
}
