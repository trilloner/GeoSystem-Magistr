package com.geosystem.springbootbackend.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class LocationRequest {

    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private String city;

}
