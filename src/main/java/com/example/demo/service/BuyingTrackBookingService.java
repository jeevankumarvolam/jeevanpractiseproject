package com.example.demo.service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.BookingPeriod;
import com.example.demo.repo.BookingRepo;
import com.example.demo.util.BookingSeasonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class BuyingTrackBookingService {
    @Autowired
    private BookingRepo bookingRepo;

    public void assignBuyingToBooking(String accId, BookingPeriod startBookingPeriod, BookingPeriod endBookingPeriod, String buyingTrack) {

        List<Booking> bookings = bookingRepo.findByAccId(accId);
        List<Booking> bookingsInCustomerChoicePeriod=new ArrayList<>();
        HashMap<String,Integer> seasonsMap= BookingSeasonHelper.seasonsMap;
        for (Booking booking : bookings) {

            if (startBookingPeriod.getYear() <= booking.getBookingPeriod().getYear() && booking.getBookingPeriod().getYear() <= endBookingPeriod.getYear()) {
                if (seasonsMap.get(booking.getBookingPeriod().getSeason()) >= seasonsMap.get(startBookingPeriod.getSeason()) && seasonsMap.get(booking.getBookingPeriod().getSeason()) <= seasonsMap.get(endBookingPeriod.getSeason())) {
                    booking.setBuyingTrack(buyingTrack);
                    bookingsInCustomerChoicePeriod.add(booking);
                }
            }
        }
        bookingRepo.saveAll(bookingsInCustomerChoicePeriod);
    }
}
