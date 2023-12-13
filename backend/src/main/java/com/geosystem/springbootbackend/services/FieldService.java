package com.geosystem.springbootbackend.services;

import com.geosystem.springbootbackend.dto.FieldRequest;
import com.geosystem.springbootbackend.models.Field;
import com.geosystem.springbootbackend.repositories.FieldRepository;
import com.geosystem.springbootbackend.utils.GeometryHelper;
import com.geosystem.springbootbackend.utils.WktToGeometry;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.geosystem.springbootbackend.utils.GeometryHelper.convertGeometryToGeoJson;

@Service
@Transactional
public class FieldService {


    private FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field saveField(FieldRequest fieldRequest) throws Exception {
        Field fieldToDb = new Field();

        if (fieldRequest.getWktToGeom() != null) {
            Geometry geom = WktToGeometry.wktToGeometry(fieldRequest.getWktToGeom());
            geom.setSRID(4326);
            fieldToDb.setPolygon(geom);
            fieldToDb.setDensity(fieldRequest.getDensity());

        }
        return fieldRepository.save(fieldToDb);
    }

    public List<Field> findAllFields() {
        return fieldRepository.findAll();
    }

    public Field findById(Long id) {
        return fieldRepository.findById(id).get();
    }

    public String getGeoJSON() {
        return fieldRepository.getGeoJsonFields();
    }

    public void deleteFieldById(Long id) {
        fieldRepository.deleteById(id);
    }

    public FeatureCollection findAllFieldsGeoJson() {
        List<Field> fieldList = fieldRepository.findAll();
        Feature[] features = mapEntityListToFeatures(fieldList);
        return new FeatureCollection(features);
    }

    public Feature getCentroids() {
        List<Field> fieldList = fieldRepository.findAll();
        Point centroid = fieldList.get(12).getPolygon().getCentroid();
        org.wololo.geojson.Geometry geometry = convertGeometryToGeoJson(centroid);
        return new Feature(1, geometry, new HashMap<>());
    }


    private Feature[] mapEntityListToFeatures(List<Field> fieldList) {
        return fieldList.stream()
                .map(GeometryHelper::convertEntityToFeature)
                .toArray(Feature[]::new);
    }

}
