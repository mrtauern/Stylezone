package com.stylezone.demo.services;

import com.stylezone.demo.models.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    //Admin
    Admin hashPassword(Admin admin);

}
