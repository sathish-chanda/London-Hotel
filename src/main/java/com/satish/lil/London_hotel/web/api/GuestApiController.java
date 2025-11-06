package com.satish.lil.London_hotel.web.api;

import com.satish.lil.London_hotel.data.entity.Guest;
import com.satish.lil.London_hotel.data.repository.GuestRepository;
import com.satish.lil.London_hotel.web.exception.BadRequestException;
import com.satish.lil.London_hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {

    private final GuestRepository guestRepository;

    public GuestApiController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@RequestBody Guest guest) {
        return guestRepository.save(guest);
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") long id) {
        Optional<Guest> guest = guestRepository.findById(id);
        if(guest.isEmpty()) {
            throw new NotFoundException("Room not found with id: " + id);
        }
        return guest.get();
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable("id") long id, @RequestBody Guest guest) {
        if(id != guest.getGuestId()) {
            throw new BadRequestException("Id on path doesn't match body");
        }
        return guestRepository.save(guest);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable("id") long id) {
        guestRepository.deleteById(id);
    }
}
