package com.geosystem.springbootbackend.services;

import com.geosystem.springbootbackend.dto.LocationRequest;
import com.geosystem.springbootbackend.models.Field;
import com.geosystem.springbootbackend.models.Location;
import com.geosystem.springbootbackend.models.User;
import com.geosystem.springbootbackend.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service

public class LocationService {
    private final LocationRepository locationRepository;
    private final FieldService fieldService;
    private final UserService userService;

    public LocationService(LocationRepository locationRepository, FieldService fieldService, UserService userService) {
        this.locationRepository = locationRepository;
        this.fieldService = fieldService;
        this.userService = userService;
    }
    @Transactional
    public Location saveLocation(LocationRequest location) {
        Location locationToDB = new Location();
        Field field = fieldService.findById(1L);
        User user = userService.findById(1L);
        locationToDB.setName(location.getName());
        locationToDB.setDescription(location.getDescription());
        locationToDB.setCity(location.getCity());
        locationToDB.setDate(Date.valueOf(LocalDate.now()));
        locationToDB.setFieldByFieldId(field);
        locationToDB.setUserByUserId(user);

        return locationRepository.save(locationToDB);
    }

    public List<Location> findAllLocations() {
        List<Location> list = locationRepository.findAll();
        return list;
    }

    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }

    public Location findLocationByFieldId(Long id) {
        Field field = this.fieldService.findById(id);
        return locationRepository.findByFieldByFieldId(field);
    }

}
