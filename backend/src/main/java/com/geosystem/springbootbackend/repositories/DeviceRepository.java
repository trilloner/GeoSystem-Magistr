package com.geosystem.springbootbackend.repositories;

import com.geosystem.springbootbackend.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
