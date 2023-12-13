package com.geosystem.springbootbackend.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;

import com.vividsolutions.jts.geom.Geometry;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@Table(name = "field", schema = "public")
public class Field {
    private Long id;
    private Double density;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry polygon;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "density")
    public Double getDensity() {
        return density;
    }

    @Basic
    @Column(name = "polygon", columnDefinition = "geometry(Polygon,4326)")

    public Geometry getPolygon() {
        return polygon;
    }

}
