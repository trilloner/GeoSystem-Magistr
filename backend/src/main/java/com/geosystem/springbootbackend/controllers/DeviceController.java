package com.geosystem.springbootbackend.controllers;


import com.geosystem.springbootbackend.dto.DeviceRequest;
import com.geosystem.springbootbackend.models.Device;
import com.geosystem.springbootbackend.repositories.DeviceRepository;
import com.geosystem.springbootbackend.services.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
@CrossOrigin(origins = "http://localhost:4200")
public class DeviceController {

    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/add")
    public ResponseEntity<Device> addDevice(@RequestBody DeviceRequest device) {
//        if (deviceToDB.getId() != null && deviceToDB.getId() != 0) {
//            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
//        }
//        if (deviceToDB.getSerialCode() == null || deviceToDB.getSerialCode().trim().length() == 0) {
//            return new ResponseEntity("missed param: name", HttpStatus.NOT_ACCEPTABLE);
//        }
        return ResponseEntity.ok(deviceService.saveDevice(device));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Device>> findAll() {
        return ResponseEntity.ok(deviceService.findAllDevices());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Device> findById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.findById(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Device> deleteDeviceById(@PathVariable Long id) {
        deviceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
