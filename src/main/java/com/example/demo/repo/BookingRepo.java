package com.example.demo.repo;


import com.example.demo.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface BookingRepo extends MongoRepository<Booking, String> {
    List<Booking> findByAccId(String accId);

}
