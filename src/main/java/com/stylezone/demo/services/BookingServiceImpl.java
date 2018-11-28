package com.stylezone.demo.services;

import com.stylezone.demo.models.Booking;
import com.stylezone.demo.models.Holiday;
import com.stylezone.demo.models.Opening;
import com.stylezone.demo.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    BookingRepo bookingRepo;

    Logger log = Logger.getLogger(BookingServiceImpl.class.getName());

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
    public Booking saveBooking(Booking booking){

        if(booking.getStaffId() > 0 ||
            !booking.getBookingEmail().equals("") ||
            !booking.getBookingTime().equals("00:00:00") ||
            !booking.getBookingDate().equals("00-00-0000") ||
            booking.getBookingPhone()>0 ||
            !booking.getBookingName().equals("")) {

            booking = bookingRepo.saveBooking(booking);

            return booking;
        }
        return null;
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

    @Override
    public void sendEmail(Booking booking) {
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("stylezone.bestilling@gmail.com", "springboot");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            String to = "stylezone.bestilling@gmail.com, "+booking.getBookingEmail();
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Din tidsbestilling hos StyleZone den " + booking.getBookingDate()+" kl. "+booking.getBookingTime());
            msg.setSentDate(new Date());
            msg.setText("Sampel System Generated mail");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            log.info("Mail has been sent successfully");
        } catch (MessagingException mex) {
            log.info("Unable to send an email" + mex);
        }
    }
}
