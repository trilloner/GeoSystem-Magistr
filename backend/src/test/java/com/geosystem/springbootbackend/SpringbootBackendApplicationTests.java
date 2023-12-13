package com.geosystem.springbootbackend;

import com.geosystem.springbootbackend.services.FieldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBackendApplicationTests {

    @Autowired
    FieldService fieldService;

    @Test
    void shouldReturnNewFeature() {
        System.out.println(fieldService.getCentroids());
    }

}
