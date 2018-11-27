package com.stylezone.demo.services;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.BookingGroup;
import com.stylezone.demo.models.Holiday;
import com.stylezone.demo.models.Opening;
import com.stylezone.demo.repositories.BookingRepo;
import com.stylezone.demo.repositories.BookingRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Booking> getSelectedBookings(String date, String timeStart, String timeEnd) {
        log.info("BookingService.getSelectedBookings("+date+", "+timeStart+", "+timeEnd+")");

        List<Booking> temp = bookingRepo.getSelectedBookings(date, timeStart, timeEnd);

        log.info("temp length: "+temp.size());

        int bookingId, bookingPhone, staffId;
        String bookingTime, bookingDate, bookingName, bookingComment;
        List<Booking> bookings = new ArrayList<>();

        int hour = Integer.parseInt(timeStart.substring(0,2));
        int start = Integer.parseInt(timeStart.substring(3,5));
        int end;
        if(Integer.parseInt(timeEnd.substring(3,5)) < 10) {
            end = 50;
        } else {
            end = Integer.parseInt(timeEnd.substring(3, 5));
            end = end - 10;
        }

        log.info("start:" + start + ", end:" + end);

        assert start < end;

        for (int i = start; i <= end; i = i+10){

            bookingTime = hour + ":" + i;
            if (i < 1) {
                bookingTime = hour + ":00";
            }

            bookingName = "";

            for (Booking t: temp) {
                if(i == Integer.parseInt(t.getBookingTime().substring(3,5))){
                    bookingName = t.getBookingName();
                }
            }

            log.info("start:" + start + ", end:" + end + ", i:" + i);
            log.info("bookingTime:" + bookingTime + ", bookingName:" + bookingName);

            bookings.add(new Booking(bookingTime, bookingName));
        }

        return bookings;
    }

    @Override
    public List<BookingGroup> getBookingGroups(String date, String timeStart, String timeEnd) {
        log.info("BookingService.getBookingGroups("+date+", "+timeStart+", "+timeEnd+")");
        List<BookingGroup> temp = bookingRepo.getBookingGroups(date, timeStart, timeEnd);
        log.info("bookingGroups length"+temp.size());

        int bookingGroupId, boookingGroupBooked, boookingGroupTotal;
        String bookingGroupStart,  bookingGroupEnd, bookingGroupDate;
        List<BookingGroup> bookingGroups = new ArrayList<>();

        for (int i = Integer.parseInt(timeStart.substring(0,2)); i <= Integer.parseInt(timeEnd.substring(0,2)); i++){

            boookingGroupTotal = 6;

            bookingGroupDate = date;

            bookingGroupStart = "" + i + ":00";
            bookingGroupEnd = "" + (i+1) + ":00";


            if(i == Integer.parseInt(timeStart.substring(0,2))){
                int param = Integer.parseInt(timeStart.substring(3,5));
                switch (param){
                    case 00:
                        boookingGroupTotal = 6;
                        break;
                    case 10:
                        boookingGroupTotal = 5;
                        bookingGroupStart = "" + i + ":10";
                        break;
                    case 20:
                        boookingGroupTotal = 4;
                        bookingGroupStart = "" + i + ":20";
                        break;
                    case 30:
                        boookingGroupTotal = 3;
                        bookingGroupStart = "" + i + ":30";
                        break;
                    case 40:
                        boookingGroupTotal = 2;
                        bookingGroupStart = "" + i + ":40";
                        break;
                    case 50:
                        boookingGroupTotal = 1;
                        bookingGroupStart = "" + i + ":50";
                        break;
                }
            }

            if(i == Integer.parseInt(timeEnd.substring(0,2))){
                int param = Integer.parseInt(timeEnd.substring(3,5));
                switch (param){
                    case 00:
                        boookingGroupTotal = 0;
                        break;
                    case 10:
                        boookingGroupTotal = 1;
                        bookingGroupEnd = "" + i + ":10";
                        break;
                    case 20:
                        boookingGroupTotal = 2;
                        bookingGroupEnd = "" + i + ":20";
                        break;
                    case 30:
                        boookingGroupTotal = 3;
                        bookingGroupEnd = "" + i + ":30";
                        break;
                    case 40:
                        boookingGroupTotal = 4;
                        bookingGroupEnd = "" + i + ":40";
                        break;
                    case 50:
                        boookingGroupTotal = 5;
                        bookingGroupEnd = "" + i + ":50";
                        break;
                }


            }

            boookingGroupBooked = 0;

            for (BookingGroup t: temp) {
                if(i == Integer.parseInt(t.getBookingGroupStart().substring(0,2))){
                    boookingGroupBooked = t.getBoookingGroupBooked();
                }
            }

            assert boookingGroupBooked <= boookingGroupTotal;

            log.info("bookingGroupStart:" + bookingGroupStart + ", bookingGroupEnd;" + bookingGroupEnd + ", bookingGroupEnd;" + bookingGroupDate + ", boookingGroupBooked:" + boookingGroupBooked + ", boookingGroupTotal" + boookingGroupTotal);

            bookingGroups.add(new BookingGroup(bookingGroupStart, bookingGroupEnd, bookingGroupDate, boookingGroupBooked, boookingGroupTotal));
        }

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
