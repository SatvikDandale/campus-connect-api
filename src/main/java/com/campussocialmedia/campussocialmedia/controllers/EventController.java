package com.campussocialmedia.campussocialmedia.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.amazonaws.Response;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.campussocialmedia.campussocialmedia.entity.EventAdd;
import com.campussocialmedia.campussocialmedia.entity.Events;
import com.campussocialmedia.campussocialmedia.exception.ExceptionResponse;
import com.campussocialmedia.campussocialmedia.service.EventService;
import com.campussocialmedia.campussocialmedia.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.SignatureException;

//endpoints:
//getPastEvents
//getUpcomingEvents
//getEvent


@RestController
@CrossOrigin
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private JwtUtil jwtUtil;

    //get all events of a particular committee
    //committee userName present in request header token
    @GetMapping("/events")
    public ResponseEntity<?> getEvents(@RequestHeader(name = "Authorization") String token) throws SignatureException {
        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);

        try{
            List<Events> events =  eventService.getCommitteeEvents(userName);
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch(Exception e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error fetching events for " + userName,
                    "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    //get a paricular event with specified eventID
    @GetMapping("/events/{eventID}")
    public ResponseEntity<?> getSpecificEvent(@PathVariable String eventID){

        try{
            Events event = eventService.getEventById(eventID);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch(Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                        "Error fetching event", "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events/pastEvents/{committeeUserName}")
    public ResponseEntity<?> getPastEvents(@PathVariable String committeeUserName){
        try{
            List<Map<String, AttributeValue>> res = eventService.getPastEventsService(committeeUserName);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch(Exception e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error fetching event",
                    "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events/upcomingEvents/{committeeUserName}")
    public ResponseEntity<?> getUpcomingEvents(@PathVariable String committeeUserName){
        try{
            List<Map<String, AttributeValue>> res = eventService.getUpcomingEventsService(committeeUserName);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error fetching event",
                    "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody EventAdd event){
        try{
            Events eventAdded = eventService.addEventService(event);
            return new ResponseEntity<>("Event added successfully",HttpStatus.OK);
        } catch(Exception e){
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Error adding event",
                    "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }
}
