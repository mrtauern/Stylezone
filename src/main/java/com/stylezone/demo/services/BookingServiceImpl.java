package com.stylezone.demo.services;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.BookingGroup;
import com.stylezone.demo.models.Holiday;
import com.stylezone.demo.models.Opening;
import com.stylezone.demo.repositories.BookingRepo;
import com.stylezone.demo.repositories.BookingRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookingServiceImpl implements BookingService {
    Logger log = Logger.getLogger(BookingRepoImpl.class.getName());


    @Autowired
    BookingRepo bookingRepo;

    @Override
    public Booking findBooking(int bookingId) {
        Booking booking = bookingRepo.findBooking(bookingId);
        return booking;
    }

    @Override
    public List<Booking> getBookings() {
        List<Booking> bookings = bookingRepo.getBookings();
        return bookings;
    }

    @Override
    public List<BookingGroup> getBookingGroups(String date, String timeStart, String timeEnd) {
        log.info("BookingService.getBookingGroups("+date+", "+timeStart+", "+timeEnd+")");
        List<BookingGroup> bookingGroups = bookingRepo.getBookingGroups(date, timeStart, timeEnd);
        log.info("bookingGroups length"+bookingGroups.size());
        return bookingGroups;
    }

    @Override
    public Booking createBooking(Booking booking) {
        booking = bookingRepo.createBooking(booking);
        return booking;
    }

    @Override
    public Booking updateBooking(Booking booking) {
        booking = bookingRepo.updateBooking(booking);
        return booking;
    }

    @Override
    public void deleteBooking(int bookingId) {
        bookingRepo.deleteBooking(bookingId);
    }

    @Override
    public Holiday findHoliday(int holidayId) {
        Holiday holiday = bookingRepo.findHoliday(holidayId);
        return holiday;
    }

    @Override
    public List<Holiday> getHolidays() {
        List<Holiday> holidays = bookingRepo.getHolidays();
        return holidays;
    }

    @Override
    public Opening findOpening(int openingId) {
        Opening opening = bookingRepo.findOpening(openingId);
        return opening;
    }

    @Override
    public List<Opening> getOpenings() {
        List<Opening> openings = bookingRepo.getOpenings();
        return openings;
    }
}
