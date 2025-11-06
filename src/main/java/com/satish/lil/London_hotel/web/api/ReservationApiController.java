package com.satish.lil.London_hotel.web.api;

import com.satish.lil.London_hotel.data.entity.Reservation;
import com.satish.lil.London_hotel.data.repository.ReservationRepository;
import com.satish.lil.London_hotel.web.exception.BadRequestException;
import com.satish.lil.London_hotel.web.exception.NotFoundException;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
    private final ReservationRepository reservationRepository;

    public ReservationApiController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAllReservation(@RequestParam(value = "date", required = false) String dateString) {
        if(StringUtils.isNotBlank(dateString)) {
            Date date = new Date(new java.util.Date().getTime());
            return reservationRepository.findAllByReservationDate(date);
        }
        return reservationRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()) {
            throw new NotFoundException("Room not found with id: " + id);
        }
        return reservation.get();
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        if(id != reservation.getGuestId()) {
            throw new BadRequestException("Id on path doesn't match body");
        }
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") long id) {
        reservationRepository.deleteById(id);
    }
}