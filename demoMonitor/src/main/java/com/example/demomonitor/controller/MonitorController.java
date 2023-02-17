package com.example.demomonitor.controller;

import com.example.demomonitor.domain.Monitor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @GetMapping("")
    public ResponseEntity<Monitor> returnMonitor(){
        Monitor monitor = new Monitor("Nero", "Sony");
        return new ResponseEntity<>(monitor, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Monitor> createMonitor(@RequestBody Monitor monitor){
        return new ResponseEntity<>(monitor, HttpStatus.CREATED);
    }

}
