package com.stylezone.demo.services;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.BookingGroup;
import com.stylezone.demo.models.Holiday;
import com.stylezone.demo.models.Opening;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    //Booking
    Booking findBooking(int bookingId);
    List<Booking> getBookings();
    List<Booking> getSelectedBookings(String date, String timeStart, String timeEnd);
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
