package com.geosystem.springbootbackend.services;

import com.geosystem.springbootbackend.dto.MeasuringRequest;
import com.geosystem.springbootbackend.models.Device;
import com.geosystem.springbootbackend.models.Field;
import com.geosystem.springbootbackend.models.Measuring;
import com.geosystem.springbootbackend.repositories.MeasuringRepository;
import com.geosystem.springbootbackend.utils.WktToGeometry;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MeasuringService {

    private final MeasuringRepository measuringRepository;
    private final DeviceService deviceService;
    private final FieldService fieldService;

    public MeasuringService(MeasuringRepository measuringRepository, DeviceService deviceService,
                            FieldService fieldService) {
        this.measuringRepository = measuringRepository;
        this.deviceService = deviceService;
        this.fieldService = fieldService;
    }

    @Transactional
    public Measuring savePoint(MeasuringRequest measuring) throws Exception {
        Device device = deviceService.findById(measuring.getDevice_id());
        Field field = fieldService.findById(measuring.getField_id());
        Measuring point = new Measuring();
        if (measuring.getWktToGeom() != null) {
            Geometry geom = WktToGeometry.wktToGeometry(measuring.getWktToGeom());
            geom.setSRID(4326);
            point.setPoint(geom);
            point.setDeviceByDeviceId(device);
            point.setFieldByFieldId(field);
        }
        return measuringRepository.save(point);
    }

    public List<Measuring> findAllMeasuring() {
        return measuringRepository.findAll();
    }
}
