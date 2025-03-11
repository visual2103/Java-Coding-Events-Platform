package com.example.hello_spring.model;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.hello_spring.model.*;


@Entity  //make this class persistent
public class Event extends AbstractEntity
{

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must been between 3 and 50 characters")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;


    @ManyToOne
    @NotNull (message = "category is required")
    private EventCategory eventCategory;

    @ManyToMany
    private final List<Tag>tags = new ArrayList<>();



    public Event(){}
    public Event(String name, String description, String contactEmail, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }

    public EventCategory getEventCategory() {  //many events for thhe same category
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<Tag> getTags() {
        return tags;
    }
    public void addTag (Tag tag)
    {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return name;
    }

}
