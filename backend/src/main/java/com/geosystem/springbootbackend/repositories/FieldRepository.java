package com.geosystem.springbootbackend.repositories;

import com.geosystem.springbootbackend.models.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    //    @Query("SELECT density, ST_Area(polygon)" +
//            " FROM field " +
//            " WHERE id= :id")
//    List<Field> findPolygon(Long id);
    @Query(value = "SELECT ST_AsGeoJSON(polygon) from field limit 3;", nativeQuery = true)
    String getGeoJsonFields();


}


