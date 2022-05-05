package com.ilyastuit.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("")
    public String api() {
        return "{ \"name\": \"App API\", \"version\": \"1.0\"}";
    }

}
