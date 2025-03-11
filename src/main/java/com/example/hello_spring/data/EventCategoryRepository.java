package com.example.hello_spring.data;

import com.example.hello_spring.model.Event;
import com.example.hello_spring.model.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> { }
