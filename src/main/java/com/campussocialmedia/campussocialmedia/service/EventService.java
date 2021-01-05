package com.campussocialmedia.campussocialmedia.service;

import java.util.List;

import com.campussocialmedia.campussocialmedia.entity.EventCard;
import com.campussocialmedia.campussocialmedia.entity.Events;
import com.campussocialmedia.campussocialmedia.repository.EventsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventsRepository eventsRepository;
    
    public Events getEventById(String eventID){
        return eventsRepository.findEventById(eventID);
    }

    public List<Events> getCommitteeEvents(String committeeUserName){
        return eventsRepository.findEventsByCommitteeUserName(committeeUserName);
    }
}
