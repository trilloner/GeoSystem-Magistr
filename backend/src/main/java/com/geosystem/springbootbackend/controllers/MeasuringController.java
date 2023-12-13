package com.geosystem.springbootbackend.controllers;

import com.geosystem.springbootbackend.dto.MeasuringRequest;
import com.geosystem.springbootbackend.models.Measuring;
import com.geosystem.springbootbackend.services.MeasuringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measuring")
@Slf4j
public class MeasuringController {

    private MeasuringService measuringService;

    public MeasuringController(MeasuringService measuringService) {
        this.measuringService = measuringService;
    }

    @PostMapping("/add")
    public ResponseEntity saveLocation(@RequestBody MeasuringRequest measuring) {
        try {
            measuringService.savePoint(measuring);
            return ResponseEntity.ok(200);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Measuring> findAllPoints() {
        return measuringService.findAllMeasuring();
    }
}
