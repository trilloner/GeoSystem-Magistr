package com.geosystem.springbootbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FieldRequest {

    @JsonProperty()
    private Double density;
    @JsonProperty()
    private String wktToGeom;

}
