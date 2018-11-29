package com.stylezone.demo.controllers;

import com.stylezone.demo.models.Admin;
import com.stylezone.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class AdminController {

    public AdminController() {

    }

    @Autowired
    AdminService adminService;

    private final String ADMINLOGIN = "adminLogin";

    Logger log = Logger.getLogger(AdminController.class.getName());

    @GetMapping("/adminLogin")
    public String adminLogin(Model model) {
        log.info("adminLogin GetMapping called...");

        model.addAttribute("admin", new Admin());

        return ADMINLOGIN;
    }

    @PostMapping
    public String adminLogin(@ModelAttribute Admin admin) {
        log.info("adminLogin PostMapping called...");

        adminService.hashPassword(admin);
        log.info("admin password hash: "+admin.getAdminPassword());

        return ADMINLOGIN;
    }



}
