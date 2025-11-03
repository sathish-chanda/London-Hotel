package com.satish.lil.London_hotel.web.controller;

import com.satish.lil.London_hotel.service.RoomReservationSerice;
import com.satish.lil.London_hotel.service.model.RoomReservation;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/occupancy")
public class OccupancyController {
    private final RoomReservationSerice roomReservationSerice;

    public OccupancyController(RoomReservationSerice roomReservationSerice) {
        this.roomReservationSerice = roomReservationSerice;
    }

    @GetMapping
    public String getOccupancy(Model model, @RequestParam(value = "date", required = false) String dateString) {
        Date date = new Date();
        if (StringUtils.isNotEmpty(dateString)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                date = format.parse(dateString);
            } catch(Exception e) {
                // do nothing
            }
        }
        model.addAttribute("date",date);
        model.addAttribute("reservations",this.roomReservationSerice.getRoomReservationsForDate(dateString));
        return "occupancy";
    }
}
