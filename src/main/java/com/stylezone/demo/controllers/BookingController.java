package com.stylezone.demo.controllers;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.services.BookingService;
import com.stylezone.demo.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    RestTemplate restTemplate;

    private final String SAVEBOOKING = "saveBooking";

    Logger log = Logger.getLogger(BookingController.class.getName());

    @GetMapping("/saveBooking/{bookingTime}/{bookingDate}")
    public String saveBooking(@PathVariable("bookingTime") String bookingTime, @PathVariable("bookingDate") String bookingDate, Model model){
        model.addAttribute("booking", new Booking());
        //model.addAttribute("booking", "book tid");
        model.addAttribute("time", bookingTime);
        model.addAttribute("date", bookingDate);


    return SAVEBOOKING;
    }

    @PostMapping("/saveBooking")
    public String saveBooking(@ModelAttribute Booking booking, Model model){

        log.info(" "+booking.getStaffId());
        bookingService.saveBooking(booking);
        //model.addAttribute("bookings", bookingService.getBookings());

        return SAVEBOOKING;

    }


}


