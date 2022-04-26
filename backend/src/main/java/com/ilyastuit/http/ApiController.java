package com.ilyastuit.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("")
    public ResponseEntity<String> api() {
        String response = "{\"name\":\"App API\",\"version\":\"1.0\"}";
        return ResponseEntity.of(Optional.of(response));
    }

}
