package com.geosystem.springbootbackend.controllers;

import com.geosystem.springbootbackend.dto.LocationRequest;
import com.geosystem.springbootbackend.models.Location;
import com.geosystem.springbootbackend.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "http://localhost:4200")

public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Location> addLocation(@RequestBody LocationRequest location) {
        Location location1 = locationService.saveLocation(location);
        return ResponseEntity.ok(location1);
    }

    @GetMapping("/all")
    public List<Location> findAllLocations() {
        return locationService.findAllLocations();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Location> deleteLocationById(@PathVariable Long id) {
        locationService.deleteLocationById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/field/{id}")
    public Location findLocationByFieldId(@PathVariable Long id) {
        return this.locationService.findLocationByFieldId(id);
    }
}
