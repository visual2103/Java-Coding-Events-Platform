package com.example.hello_spring.data;
import com.example.hello_spring.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer>  //the obj and the data type for primary key
{ }//public class MyRepository implements EventRepository
