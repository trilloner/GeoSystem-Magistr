package com.geosystem.springbootbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceRequest {

    @JsonProperty()
    private String serialCode;
}
