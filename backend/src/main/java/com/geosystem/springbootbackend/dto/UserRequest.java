package com.geosystem.springbootbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
public class UserRequest {

    @JsonProperty()
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private Date date;
    @JsonProperty
    private String password;
}
