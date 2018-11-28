package com.stylezone.demo.controllers;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.BookingGroup;
import com.stylezone.demo.services.BookingService;
import com.stylezone.demo.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;

    private final String REDIRECT = "redirect:/";
    private final String INDEX = "index";
    private final String BOOKING = "booking";
    private final String TIMESELECT = "timeSelect";

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
        String[] dates = bookingService.getDatesOfWeek();
        model.addAttribute("monday", dates[0]);
        model.addAttribute("tuesday", dates[1]);
        model.addAttribute("wednesday", dates[2]);
        model.addAttribute("thursday", dates[3]);
        model.addAttribute("friday", dates[4]);
        model.addAttribute("saturday", dates[5]);
        model.addAttribute("sunday", dates[6]);
        model.addAttribute("weekNumber",bookingService.getWeekToday());
        model.addAttribute("mondayBookings", bookingGroups);
        model.addAttribute("pageTitle", "Book tid");

        log.info(bookingService.getDateToday());

        return BOOKING;
    }

    @GetMapping("/timeSelect/{date}/{start}/{end}")
    public String timeSelect(@PathVariable String date, @PathVariable String start, @PathVariable String end, Model model) {
        log.info("timeSelect called...");

        List<Booking> bookings = bookingService.getSelectedBookings(date, start, end);
        model.addAttribute("bookings", bookings);
        model.addAttribute("date", date);
        model.addAttribute("pageTitle", "Tider for " + date + ", mellem kl." + start + " - " + end);

        return TIMESELECT;
    }
}
