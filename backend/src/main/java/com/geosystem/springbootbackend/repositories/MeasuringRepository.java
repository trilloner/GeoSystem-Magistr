package com.geosystem.springbootbackend.repositories;

import com.geosystem.springbootbackend.models.Measuring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasuringRepository extends JpaRepository<Measuring,Long > {
}
