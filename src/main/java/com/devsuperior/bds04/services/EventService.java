package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    public EventDTO insert(EventDTO eventDTO) {
        Event entity = new Event();

        //TIVE QUE REALIZAR DUAS CONSULTAS A BASE PRA CONSEGUIR LANÇAR O OBJETO ESPERADO NA LINHA 33, ALGUMA OUTRA FORMA DE FAZER???
        City city = cityRepository.findById(eventDTO.getCityId()).get();

        entity.setName(eventDTO.getName());
        entity.setDate(eventDTO.getDate());
        entity.setUrl(eventDTO.getUrl());
        entity.setCity(city);

        entity = eventRepository.save(entity);

        return new EventDTO(entity);
    }


}
