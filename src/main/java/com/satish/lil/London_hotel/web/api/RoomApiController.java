package com.satish.lil.London_hotel.web.api;

import com.satish.lil.London_hotel.data.entity.Room;
import com.satish.lil.London_hotel.data.repository.RoomRepository;
import com.satish.lil.London_hotel.web.exception.BadRequestException;
import com.satish.lil.London_hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {
    private final RoomRepository roomRepository;

    public RoomApiController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isEmpty()) {
            throw new NotFoundException("Room not found with id: " + id);
        }
        return room.get();
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
        if(id != room.getId()) {
            throw new BadRequestException("Id on path doesn't match body");
        }
        return roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") long id) {
        roomRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Room partiallyUpdateRoom(@PathVariable("id") long id, @RequestBody Room room) {
        Optional<Room> existingRoom = roomRepository.findById(id);

        Room oldRoom;

        if(existingRoom.isPresent()) {
           oldRoom = existingRoom.get();
        } else {
            throw new NotFoundException("Room not found with id: " + id);
        }


        if(room.getName() != null) {
            oldRoom.setName(room.getName());
        }

        if(room.getRoomNumber() != null) {
            oldRoom.setRoomNumber(room.getRoomNumber());
        }

        if(room.getBedInfo() != null) {
            oldRoom.setBedInfo(room.getBedInfo());
        }

        return roomRepository.save(oldRoom);
    }
}
