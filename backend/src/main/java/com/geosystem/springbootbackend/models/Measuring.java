package com.geosystem.springbootbackend.models;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@Table(name = "measuring", schema = "public")
public class Measuring {
    private Long id;
    private Geometry point;
    private Field fieldByFieldId;
    private Device deviceByDeviceId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "point" , columnDefinition = "geometry(Point, 4326)")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize( using = GeometryDeserializer.class)
    public Geometry getPoint() {
        return point;
    }


    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    public Field getFieldByFieldId() {
        return fieldByFieldId;
    }


    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    public Device getDeviceByDeviceId() {
        return deviceByDeviceId;
    }

}
