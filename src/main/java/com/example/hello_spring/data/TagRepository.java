package com.example.hello_spring.data;

import com.example.hello_spring.model.Event;
import com.example.hello_spring.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Integer>  {
}
