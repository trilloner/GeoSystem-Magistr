package com.geosystem.springbootbackend.services;

import com.geosystem.springbootbackend.dto.DeviceRequest;
import com.geosystem.springbootbackend.models.Device;
import com.geosystem.springbootbackend.models.Field;
import com.geosystem.springbootbackend.repositories.DeviceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeviceService {

    private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device saveDevice(DeviceRequest deviceRequest) {
        Device deviceToDB = new Device();
//
        deviceToDB.setSerialCode(deviceRequest.getSerialCode());
        return deviceRepository.save(deviceToDB);
    }

    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    public Device findById(Long id) {
        return deviceRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        deviceRepository.deleteById(id);
    }
}
