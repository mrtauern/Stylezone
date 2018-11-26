package com.stylezone.demo.controllers;

import com.stylezone.demo.models.BookingGroup;
import com.stylezone.demo.services.BookingService;
import com.stylezone.demo.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;

    private final String REDIRECT = "redirect:/";
    private final String INDEX = "index";
    private final String BOOKING = "booking";

    Logger log = Logger.getLogger(BookingController.class.getName());

    public BookingController() {
    }

    @GetMapping("/")
    public String index(Model model) {
        log.info("Index called...");

        model.addAttribute("pageTitle", "Forside");
        model.addAttribute("isIndex", true);

        return INDEX;
    }

    @GetMapping("/booking")
    public String booking(Model model) {
        log.info("booking called...");

        List<BookingGroup> bookingGroups = bookingService.getBookingGroups("12-12-2018", "10:00", "18:30");
        model.addAttribute("monday", bookingGroups);
        model.addAttribute("pageTitle", "Book tid");

        return BOOKING;
    }
}
