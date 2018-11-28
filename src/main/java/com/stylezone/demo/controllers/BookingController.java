package com.stylezone.demo.controllers;

import com.stylezone.demo.services.BookingService;
import com.stylezone.demo.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;
    private String billedGalleri;
    private String index;


    public BookingController() { }

    Logger log = Logger.getLogger(BookingController.class.getName());

    @GetMapping("/")
    public String index(Model model){
        log.info("index called");

        return index;        
    }
    
    @GetMapping("/billedGalleri")
    public String billedGalleri(Model model) {
        log.info("billedGalleri called...");

        return billedGalleri;

    }

}
