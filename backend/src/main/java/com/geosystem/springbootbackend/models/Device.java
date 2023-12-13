package com.geosystem.springbootbackend.models;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@Table(name = "device", schema = "public")
public class Device {
    private Long id;
    private String serialCode;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "serial_code")
    public String getSerialCode() {
        return serialCode;
    }

}
