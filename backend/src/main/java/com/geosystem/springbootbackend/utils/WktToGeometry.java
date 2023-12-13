package com.geosystem.springbootbackend.utils;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

@Slf4j
public class WktToGeometry {
    //convert to bean
    public static Geometry wktToGeometry(String wktPolygon) throws Exception {
        WKTReader fromText = new WKTReader();
        Geometry geometry = null;
        try {
            geometry = fromText.read(wktPolygon);
            if (!geometry.isSimple() && !geometry.isValid()) {
                throw new Exception("Polygon is not simple");
            }

        } catch (ParseException e) {
            log.info("Cannot parse geometry");
        }
        return geometry;
    }
}
