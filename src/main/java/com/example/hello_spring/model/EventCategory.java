package com.example.hello_spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity
{
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();

    public EventCategory( @Size(min=3, message="Name must be at least 3 characters long")String name){
        this.name=name;
    }
    public EventCategory(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }  //no setter cuz this is final
    //no modify my constructor cuz i create this in the field decoration

    @Override
    public String toString() {
        return name;
    }
}
