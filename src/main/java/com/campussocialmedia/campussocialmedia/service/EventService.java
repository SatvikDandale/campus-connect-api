package com.campussocialmedia.campussocialmedia.service;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.campussocialmedia.campussocialmedia.entity.EventAdd;
import com.campussocialmedia.campussocialmedia.entity.Events;
import com.campussocialmedia.campussocialmedia.repository.EventsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Events convertToEntity(EventAdd eventAdd) {
        return modelMapper.map(eventAdd, Events.class);
    }
    
    public Events getEventById(String eventID){
        return eventsRepository.findEventById(eventID);
    }

    public List<Events> getCommitteeEvents(String committeeUserName){
        return eventsRepository.findEventsByCommitteeUserName(committeeUserName);
    }

    public List<Map<String, AttributeValue>> getPastEventsService(String committeeUserName){
        return eventsRepository.getPastEventsRepo(committeeUserName);
    }

    public List<Map<String,AttributeValue>> getUpcomingEventsService(String committeeUserName) {
        return eventsRepository.getUpcomingEventsRepo(committeeUserName);
    }

    public Events addEventService(EventAdd event){
        Events convertedEvent = convertToEntity(event);
        // System.out.println(convertedEvent);

        return eventsRepository.addEventRepo(convertedEvent);
    }
}
