package com.example.area.areabackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.area.areabackend.dao")
public class AreaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AreaBackendApplication.class, args);
    }
}
