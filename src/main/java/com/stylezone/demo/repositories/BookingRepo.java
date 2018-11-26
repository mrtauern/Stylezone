package com.stylezone.demo.repositories;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.BookingGroup;
import com.stylezone.demo.models.Holiday;
import com.stylezone.demo.models.Opening;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookingRepo {
    //Booking
    Booking findBooking(int bookingId);
    List<Booking> getBookings();
    List<BookingGroup> getBookingGroups(String date, String timeStart, String timeEnd);
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    void deleteBooking(int bookingId);

    //Holiday
    Holiday findHoliday(int holidayId);
    List<Holiday> getHolidays();
    /*Holiday createHoliday(Holiday holiday);
    Holiday updateHoliday(Holiday holiday);
    void deleteHoliday(int holidayId);*/

    //Opening
    Opening findOpening(int openingId);
    List<Opening> getOpenings();

}
