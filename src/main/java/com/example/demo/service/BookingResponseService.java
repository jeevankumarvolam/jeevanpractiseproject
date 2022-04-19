package com.example.demo.service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Links;
import com.example.demo.entity.Self;
import com.example.demo.entity.response.BookingResponse;
import com.example.demo.exceptionfamily.BookingNotFoundException;
import com.example.demo.repo.BookingRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class BookingResponseService {

    private final BookingRepo bookingRepo;
    @Value("${self.link}")
    private String selfLink;

    public BookingResponseService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public BookingResponse createBookingResponse(String id) throws BookingNotFoundException {
        BookingResponse.BookingResponseBuilder bookingResponse = BookingResponse.builder();
        Optional<Booking> entity = bookingRepo.findById(id);
        if (entity.isPresent()) {
            //create booking response
            Hashtable<String,Integer> a = new Hashtable<>();
            HashMap<String,Integer> k = new HashMap<>();
          
            bookingResponse.id(entity.get().getId()).
                    accId(entity.get().getAccId()).
                    bookingSeason(entity.get().getBookingPeriod().getSeason()).
                    bookingYear(entity.get().getBookingPeriod().getYear()).
                    createdAt(entity.get().getCreatedAt()).
                    createdBy(entity.get().getCreatedBy()).
                    modifiedBy(entity.get().getModifiedBy()).
                    modifiedAt(entity.get().getModifiedAt()).links(Links.builder().
                            self(Self.builder().href(selfLink + id).build()).build()).transientVariable(entity.get().getCheck());

            return bookingResponse.build();
        } else {
            throw new BookingNotFoundException("Booking not found with id " + id);
        }


    }

}
