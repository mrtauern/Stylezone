package com.stylezone.demo.controllers;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.ReCaptchaResponse;
import com.stylezone.demo.models.BookingGroup;
import com.stylezone.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class BookingController {

    public BookingController() {
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BookingService bookingService;

    private final String REDIRECT = "redirect:/";
    private final String SAVEBOOKING = "saveBooking";
    private final String BOOKING = "booking";
    private final String TIMESELECT = "timeSelect";
    private final String BILLEDEGALLERI = "billedeGalleri";
    private final String INDEX = "index";
    private final String OMOS = "omOs";


    Logger log = Logger.getLogger(BookingController.class.getName());

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

        String date = bookingService.getDateToday();
        int weekNumber = bookingService.getWeekToday();
        List<BookingGroup> bookingGroups;
        String[] dates = bookingService.getDatesOfWeek();

        bookingGroups = bookingService.getBookingGroups(dates[0], "10:00", "18:30");
        model.addAttribute("mondayBookings", bookingGroups);
        model.addAttribute("monday", dates[0]);
        bookingGroups = bookingService.getBookingGroups(dates[1], "10:00", "18:30");
        model.addAttribute("tuesdayBookings", bookingGroups);
        model.addAttribute("tuesday", dates[1]);
        bookingGroups = bookingService.getBookingGroups(dates[2], "10:00", "18:30");
        model.addAttribute("wednesdayBookings", bookingGroups);
        model.addAttribute("wednesday", dates[2]);
        bookingGroups = bookingService.getBookingGroups(dates[3], "10:00", "18:30");
        model.addAttribute("thursdayBookings", bookingGroups);
        model.addAttribute("thursday", dates[3]);
        bookingGroups = bookingService.getBookingGroups(dates[4], "10:00", "18:30");
        model.addAttribute("fridayBookings", bookingGroups);
        model.addAttribute("friday", dates[4]);
        bookingGroups = bookingService.getBookingGroups(dates[5], "10:00", "18:30");
        model.addAttribute("saturdayBookings", bookingGroups);
        model.addAttribute("saturday", dates[5]);

        model.addAttribute("sunday", dates[6]);
        model.addAttribute("nextWeek",bookingService.nextWeek());
        model.addAttribute("prevWeek",bookingService.prevWeek());
        model.addAttribute("weekNumber",weekNumber);
        model.addAttribute("pageTitle", "Book tid");

        //log.info(bookingService.getDateToday());

        return BOOKING;
    }

    @GetMapping("/booking/{day}-{month}-{year}")
    public String bookingDate(@PathVariable("day") int day, @PathVariable("month") int month, @PathVariable("year") int year, Model model) {
        log.info("booking called...");

        String date = day + "-" + month + "-" + year;
        int weekNumber = bookingService.getWeekFromDate(day, month, year);
        List<BookingGroup> bookingGroups;
        String[] dates = bookingService.getDatesOfSelectedWeek(day, month, year);


        bookingGroups = bookingService.getBookingGroups(dates[0], "10:00", "18:30");
        model.addAttribute("mondayBookings", bookingGroups);
        model.addAttribute("monday", dates[0]);
        bookingGroups = bookingService.getBookingGroups(dates[1], "10:00", "18:30");
        model.addAttribute("tuesdayBookings", bookingGroups);
        model.addAttribute("tuesday", dates[1]);
        bookingGroups = bookingService.getBookingGroups(dates[2], "10:00", "18:30");
        model.addAttribute("wednesdayBookings", bookingGroups);
        model.addAttribute("wednesday", dates[2]);
        bookingGroups = bookingService.getBookingGroups(dates[3], "10:00", "18:30");
        model.addAttribute("thursdayBookings", bookingGroups);
        model.addAttribute("thursday", dates[3]);
        bookingGroups = bookingService.getBookingGroups(dates[4], "10:00", "18:30");
        model.addAttribute("fridayBookings", bookingGroups);
        model.addAttribute("friday", dates[4]);
        bookingGroups = bookingService.getBookingGroups(dates[5], "10:00", "18:30");
        model.addAttribute("saturdayBookings", bookingGroups);
        model.addAttribute("saturday", dates[5]);

        model.addAttribute("sunday", dates[6]);
        model.addAttribute("nextWeek",bookingService.nextWeekFromDate(day, month, year));
        model.addAttribute("prevWeek",bookingService.prevWeekFromDate(day, month, year));
        model.addAttribute("weekNumber",weekNumber);
        model.addAttribute("pageTitle", "Book tid");

        //log.info(bookingService.getDateToday());

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

    @GetMapping("/billedeGalleri")
    public String billedGalleri() {
        log.info("billedeGalleri called...");

        return BILLEDEGALLERI;

    }


    @GetMapping("/saveBooking/{bookingTime}/{bookingDate}")
    public String saveBooking(@PathVariable("bookingTime") String bookingTime, @PathVariable("bookingDate") String bookingDate, Model model){

        log.info("saveBooking getmapping called...");

        model.addAttribute("booking", new Booking());

        model.addAttribute("time", bookingTime);
        model.addAttribute("date", bookingDate);


        return SAVEBOOKING;
    }

    @PostMapping("/saveBooking")
    public String saveBooking(@ModelAttribute Booking booking,
                              @RequestParam("g-recaptcha-response") String captchaResponse){

        log.info("saveBooking postmapping called...");

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LeWE30UAAAAAMUpo7seu91Da6DXig-DQxN8YKEQ&response="+captchaResponse;

        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();

        if(reCaptchaResponse.isSuccess()) {

            bookingService.saveBooking(booking);
            bookingService.sendEmail(booking);
            return REDIRECT+BOOKING;
        }


        return SAVEBOOKING;

    }

    @GetMapping("/omOs")
    public String omOs(Model model) {

        return OMOS;

    }
}


