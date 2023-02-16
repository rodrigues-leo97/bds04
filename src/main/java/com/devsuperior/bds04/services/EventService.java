package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventDTO insert(EventDTO eventDTO) {
        Event entity = new Event();

        entity.setName(eventDTO.getName());
        entity.setDate(eventDTO.getDate());
        entity.setUrl(eventDTO.getUrl());

//        for(CityDTO cityDTO : eventDTO.get) {
//            Event event = eventRepository.getOne(eventDTO.getId());
//        }

        entity = eventRepository.save(entity);

        return new EventDTO(entity);
    }


}
