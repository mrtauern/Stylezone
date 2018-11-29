package com.stylezone.demo.services;

import com.stylezone.demo.models.Admin;
import com.stylezone.demo.repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    Logger log = Logger.getLogger(AdminServiceImpl.class.getName());

    @Override
    public Admin hashPassword(Admin admin) {
        String passwordHash = admin.getAdminPassword();

        admin.setAdminPassword(""+passwordHash.hashCode());
        log.info( "passwordHash: "+passwordHash.hashCode()+" admin password hashed: "+admin.getAdminPassword());

        return admin;
    }
}
