package com.ilyastuit.backend.controller;

import com.ilyastuit.backend.dto.App;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("")
    public ResponseEntity<App> home() {
        return ResponseEntity.ok(new App("App", "1.0"));
    }

}
