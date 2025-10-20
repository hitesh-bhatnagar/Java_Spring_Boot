package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//  <       !!! Spring will now automatically create one reusable ModelMapper instance (a Bean) we can inject anywhere        >

@Configuration  // annotation is applied to a class to indicate that it contains method-level @Bean definitions. This class acts as a configuration source for the Spring Inversion of Control (IoC) container.

public class ModelMapperConfig {
    
    @Bean // Method applied to @Configuration class. Returns a new object that will be managed by the Spring Container. 
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
