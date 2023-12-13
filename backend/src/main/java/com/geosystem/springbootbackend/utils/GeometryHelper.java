package com.geosystem.springbootbackend.utils;

import com.geosystem.springbootbackend.models.Field;
import com.vividsolutions.jts.geom.Geometry;
import org.wololo.geojson.Feature;
import org.wololo.jts2geojson.GeoJSONReader;
import org.wololo.jts2geojson.GeoJSONWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GeometryHelper {

    public static org.wololo.geojson.Geometry convertGeometryToGeoJson(Geometry geometry) {
        return new GeoJSONWriter().write(geometry);
    }


    public static Geometry convertGeoJsonToGeometry(org.wololo.geojson.Geometry geoJson) {
        return new GeoJSONReader().read(geoJson);
    }

    public static Feature convertEntityToFeature(Field field) {
        Long id = field.getId();
        org.wololo.geojson.Geometry geometryForJson = convertGeometryToGeoJson(field.getPolygon());
        Map<String, Object> properties = new HashMap<>();
        Arrays.stream(Field.class.getDeclaredFields()).filter(i -> !i.isSynthetic()).forEach(
                object -> {
                    try {
                        object.setAccessible(true);
                        if (object.getType() != Geometry.class && !object.getName().equals("id"))
                            properties.put(object.getName(), object.get(field));
                    } catch (IllegalAccessException e) {
                        System.out.println("error");
                    }
                }
        );
        return new Feature(id, geometryForJson, properties);
    }

}
