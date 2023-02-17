package com.example.demomonitor.controller;

import com.example.demomonitor.domain.Canale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canale")
public class CanaleController {
    @GetMapping("")
    public ResponseEntity<Canale> returnMonitor(){
        Canale canale = new Canale("Rai1", 1, true);
        return new ResponseEntity<>(canale, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Canale> createMonitor(@RequestBody Canale canale){
        return new ResponseEntity<>(canale, HttpStatus.CREATED);
    }

}
