package com.geosystem.springbootbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeasuringRequest {

    @JsonProperty()
    private String wktToGeom;
    @JsonProperty()
    private Long field_id;
    @JsonProperty()
    private Long device_id;         //уточнить вопрос по вложенным классам
}
