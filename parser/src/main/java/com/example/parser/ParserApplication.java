package com.example.parser;

import com.example.parser.properties.FileStorageProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperty.class})
public class ParserApplication {

  public static void main(String[] args) {
    SpringApplication.run(ParserApplication.class, args);
  }

}
