package com.stylezone.demo.repositories;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.Holiday;
import com.stylezone.demo.models.Opening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.logging.Logger;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository

public class BookingRepoImpl implements BookingRepo {
    Logger log = Logger.getLogger(BookingRepoImpl.class.getName());


    @Autowired
    JdbcTemplate template;

    @Override
    public Booking findBooking(int bookingId) {
        String sql = "SELECT * FROM booking WHERE BookingId = ?";
        RowMapper<Booking> rowMapper = new BeanPropertyRowMapper<>(Booking.class);

        Booking booking = template.queryForObject(sql, rowMapper, bookingId);


        return booking;
    }


    @Override
    public List<Booking> getBookings() {
        String sql = "SELECT * FROM Booking";
        return this.template.query(sql, new ResultSetExtractor<List<Booking>>() {

            @Override
            public List<Booking> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int bookingId, bookingPhone, staffId;
                String bookingTime, bookingDate, bookingName, bookingComment;
                ArrayList<Booking> bookings = new ArrayList<>();

                while (rs.next()) {
                    bookingId = rs.getInt("bookingId");
                    bookingPhone = rs.getInt("bookingPhone");
                    staffId = rs.getInt("staffId");
                    bookingTime = rs.getString("bookingTime");
                    bookingDate = rs.getString("bookingDate");
                    bookingName = rs.getString("bookingName");
                    bookingComment = rs.getString("bookingComment");

                    bookings.add(new Booking(bookingId, bookingTime, bookingDate, bookingName, bookingPhone, bookingComment, staffId));
                }
                return bookings;
            }
        });
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
        String sql = "SELECT * FROM holiday WHERE holidayId = ?";
        RowMapper<Holiday> rowMapper = new BeanPropertyRowMapper<>(Holiday.class);

        Holiday holiday = template.queryForObject(sql, rowMapper, holidayId);


        return holiday;
    }

    @Override
    public List<Holiday> getHolidays() {
        String sql = "SELECT * FROM Holiday";
        return this.template.query(sql, new ResultSetExtractor<List<Holiday>>() {

            @Override
            public List<Holiday> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int holidayId;
                String holidayDate, holidayName;
                ArrayList<Holiday> holidays = new ArrayList<>();

                while (rs.next()) {
                    holidayId = rs.getInt("holidayId");
                    holidayDate = rs.getString("holidayDate");
                    holidayName = rs.getString("holidayName");


                    holidays.add(new Holiday(holidayId, holidayDate, holidayName));
                }
                return holidays;
            }
        });
    }

    @Override
    public Opening findOpening(int openingId) {
        String sql = "SELECT * FROM opening WHERE openingId = ?";
        RowMapper<Opening> rowMapper = new BeanPropertyRowMapper<>(Opening.class);

        Opening opening = template.queryForObject(sql, rowMapper, openingId);


        return opening;
    }

    @Override
    public List<Opening> getOpenings() {
        String sql = "SELECT * FROM opening";
        return this.template.query(sql, new ResultSetExtractor<List<Opening>>() {

            @Override
            public List<Opening> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int openingId, openingTime, openingClose;
                String openingDay;
                ArrayList<Opening> openings = new ArrayList<>();

                while (rs.next()) {

                    openingId = rs.getInt("openingId");
                    openingDay = rs.getString("openingDay");
                    openingTime = rs.getInt("openingTime");
                    openingClose = rs.getInt("openingClose");

                    openings.add(new Opening(openingId, openingDay, openingTime, openingClose));
                }
                return openings;
            }
        });
    }
}


