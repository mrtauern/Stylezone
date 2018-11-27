package com.stylezone.demo.controllers;

import com.stylezone.demo.models.ReCaptchaResponse;
import com.stylezone.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    RestTemplate restTemplate;

    private final String test = "testReCaptcha";

    Logger log = Logger.getLogger(BookingController.class.getName());

    @GetMapping("/testReCaptcha")
    public String testReCapcha() {
        log.info("Recaptchatest called");
        // https://www.youtube.com/watch?v=5wZWAqEwBj8

        return test;
    }

    @RequestMapping(value = "/testReCaptcha", method = RequestMethod.POST)
    public String testReCaptcha(@RequestParam("g-recaptcha-response") String captchaResponse) {

        //https://www.youtube.com/watch?v=5wZWAqEwBj8
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LeWE30UAAAAAMUpo7seu91Da6DXig-DQxN8YKEQ&response="+captchaResponse;

        log.info("testReCaptcha POST called... reCaptchaResponse = "+captchaResponse);

        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();

        if(reCaptchaResponse.isSuccess()) {
            log.info("recaptcha robot check is a success");

            return "success";
        }

        return test;
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
