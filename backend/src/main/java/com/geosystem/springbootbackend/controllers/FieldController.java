package com.geosystem.springbootbackend.controllers;

import com.geosystem.springbootbackend.dto.FieldRequest;
import com.geosystem.springbootbackend.models.Field;
import com.geosystem.springbootbackend.services.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wololo.geojson.FeatureCollection;

import java.util.List;

@RestController
@RequestMapping("/field")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class FieldController {

    private FieldService fieldService;

    public FieldController(FieldService field) {
        this.fieldService = field;
    }

    @PostMapping("/add")
    public ResponseEntity addField(@RequestBody FieldRequest field) {
        try {
            Field field1 = fieldService.saveField(field);
            return ResponseEntity.ok(field1.getId());

        } catch (Exception exception) {
            log.error("Field can`t be created: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<Field> showAll() {
        return fieldService.findAllFields();
    }

    @GetMapping("/geo")
    public ResponseEntity<String> showGeoJson() {

        return ResponseEntity.ok(fieldService.getGeoJSON());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteByFieldId(@PathVariable Long id) {
        fieldService.deleteFieldById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/geojson/all")
    public ResponseEntity<FeatureCollection> getAllLocations() {
        return new ResponseEntity<>(fieldService.findAllFieldsGeoJson(), HttpStatus.OK);
    }


}
