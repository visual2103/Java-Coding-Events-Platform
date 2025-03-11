package com.example.hello_spring.controllers;

import com.example.hello_spring.data.EventCategoryRepository;
import com.example.hello_spring.data.EventData;
import com.example.hello_spring.data.EventRepository;
import com.example.hello_spring.data.TagRepository;
import com.example.hello_spring.model.Event;
import com.example.hello_spring.model.EventCategory;
import com.example.hello_spring.model.Tag;
import com.example.hello_spring.model.dto.EventTagDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventsController
{
    @Autowired   //dependency injection = inversion of control
    //find all, save, find by id
    private EventRepository eventRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    //private static List<Event> events = new ArrayList<>();  //only exists when the app run. when we restart the app, we gonna lose all events
    @GetMapping
    public String  displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model){
        if(categoryId == null){
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());}
        else{
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId); // java returns something when in database is nothing
            if(result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID " + categoryId);
            } else{
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";  //cand vreau sa ma refer la un form dintr un director.
    }

    //lives at /events/create
    @GetMapping("create")
    public String displayCreateEvent(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    //lives at /events/create ~ it s ok to have the same path cuz we have different types of request
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid  Event newEvent, Errors errors, Model model) {
        //This errors object will be populated by any validation errors that occur when Spring Boot validates the object according to the validation that we set up
        //ModelAtribute: when spring try to call this method it will look in the request data and look for fields that mach up with the field of the Event class

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        eventRepository.save(newEvent); //spring will create the newEvent for us
        return "redirect:/events";   //a redirect response. we redirected them
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null){
            for(int id : eventIds){
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/events";
    }

    @GetMapping("details")
    public String displayEventDetails(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        if(result.isEmpty()){
            model.addAttribute("title", "invalid Event ID" + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }
        return "events/details";

    }

    //responds to /events/add-tag?eventId=
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result =  eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag To: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTagDTO, Errors errors, Model model){
        if (!errors.hasErrors()) {
            Event event = eventTagDTO.getEvent();
            Tag tag = eventTagDTO.getTag();

            // Adaugă tag-ul la eveniment, dacă nu este deja adăugat
            if (!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventRepository.save(event);  // Salvează modificările în eveniment
            }
            return "redirect:/events/details?eventId=" + event.getId();  // Redirecționează către detalii
        }
        return "redirect:/events/add-tag?eventId=" + eventTagDTO.getEvent().getId();  // Dacă sunt erori, rămâi pe formular
    }



}
