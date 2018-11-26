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

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    private final String SAVEBOOKING = "saveBooking";

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


        bookingService.saveBooking(booking);
        //model.addAttribute("bookings", bookingService.getBookings());

        return SAVEBOOKING;

    }


}


