package com.example.hello_spring.data;

import com.example.hello_spring.model.Event;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EventData
{
    //it only contains static methods. it is a single point of operations of how the objects will be restored
    //need a place tu put events
    private static final Map<Integer, Event> events = new HashMap<>(); //final: once this map is initiated,  it can t change
    //get all events
    public static Collection<Event> getAll(){
        //collection: interface. iterable
        //interfata mai generala ca List. se pot folosi Set, Queue sau alte tipuri de colectii, nu doar List
        //daca metoda ar fi def sa return List, ar trebui convertita explicit colectia intr un list
        //cu collection ascund detaliile implementarii. codul ce o foloseste nu trebuie sa stie daca intern se folosesc liste, seturi etc.
        //daca in viitor doresc sa schimb implementarea, de ex sa nu mai folosesc HashMap, asta nu mi va afecta colectia
        return events.values();
    }
    //get a single event
    public static Event getById (int id){
        return events.get(id); //returnam itemul cu id ul specific
    }
    // add an event
    public static void add (Event event){
        events.put(event.getId(), event);
    }
    //remove an event
    public static void remove(int id){
        events.remove(id);
    }


}
