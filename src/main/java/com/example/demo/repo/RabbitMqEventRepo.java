package com.example.demo.repo;
import com.example.demo.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RabbitMqEventRepo extends MongoRepository<Event, String> {
}
