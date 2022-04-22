package com.example.demo.service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.BookingPeriod;
import com.example.demo.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class BuyingTrackBookingService {
    @Autowired
    private BookingRepo bookingRepo;

    public void addBuyingTrackToBooking(String accId, BookingPeriod startBookingPeriod, BookingPeriod endBookingPeriod, String buyingTrack) {
        HashMap<String, Integer> seasonsMap = new HashMap<>();
        seasonsMap.put("SPRING", 1);
        seasonsMap.put("SUMMER", 2);
        seasonsMap.put("FALL", 3);
        seasonsMap.put("HOLIDAY", 4);
        List<Booking> bookings = bookingRepo.findByAccId(accId);
        for (Booking i : bookings) {

            if (startBookingPeriod.getYear() <= i.getBookingPeriod().getYear() && i.getBookingPeriod().getYear() <= endBookingPeriod.getYear()) {
                if (seasonsMap.get(i.getBookingPeriod().getSeason()) >= seasonsMap.get(startBookingPeriod.getSeason()) && seasonsMap.get(i.getBookingPeriod().getSeason()) <= seasonsMap.get(endBookingPeriod.getSeason())) {
                    i.setBuyingTrack(buyingTrack);
                    bookingRepo.save(i);
                }


            }


        }

    }


}
