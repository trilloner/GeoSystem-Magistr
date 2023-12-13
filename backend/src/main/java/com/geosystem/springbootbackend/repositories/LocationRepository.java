package com.geosystem.springbootbackend.repositories;

import com.geosystem.springbootbackend.models.Field;
import com.geosystem.springbootbackend.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByFieldByFieldId(Field field);
}
