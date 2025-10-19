// The @SpringBootApplication annotation is a convenience annotation that combines three other key annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan. 

// It is used on the main class of a Spring Boot application to mark the entry point and enable all three core features simultaneously.


package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;  // Annotation for mapping HTTP GET requests onto specific handler methods
import org.springframework.web.bind.annotation.RestController; // A convenience annotation that is itself annotated with @Controller and @ResponseBody


@RestController // tells spring this class handles HTTP requests
public class HelloController {

    @GetMapping("/hello") // maps this method to the /hello URL 
    public String say() {
        return "Hello spring boot is working";  // returning a string sends it directly as the HTTP response
    }
    
}
