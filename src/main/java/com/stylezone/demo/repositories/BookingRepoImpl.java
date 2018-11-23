package com.stylezone.demo.repositories;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.Holiday;
import com.stylezone.demo.models.Opening;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingRepoImpl implements BookingRepo {
    @Override
    public Booking findBooking(int bookingId) {
        return null;
    }

    @Override
    public List<Booking> getBookings() {
        return null;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return null;
    }

    @Override
    public void deleteBooking(int bookingId) {

    }

    @Override
    public Holiday findHoliday(int holidayId) {
        return null;
    }

    @Override
    public List<Holiday> getHolidays() {
        return null;
    }

    @Override
    public Opening findOpening(int openingId) {
        return null;
    }

    @Override
    public List<Opening> getOpenings() {
        return null;
    }
}
