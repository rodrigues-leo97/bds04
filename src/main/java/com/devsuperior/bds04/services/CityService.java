package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public List<CityDTO> findAll() {
        List<City> city = cityRepository.findAll(Sort.by("name"));

        return city.stream()
                .map(x -> new CityDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO cityDTO) {
        City entity = new City();
        entity.setName(cityDTO.getName());
        entity = cityRepository.save(entity);

        return new CityDTO(entity);
    }
}
