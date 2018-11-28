package com.stylezone.demo.controllers;

import com.stylezone.demo.services.BookingService;
import com.stylezone.demo.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;


    public BookingController() { }


    Logger log = Logger.getLogger(BookingController.class.getName());


}
