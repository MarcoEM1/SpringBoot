package com.example.demomonitor.controller;

import com.example.demomonitor.domain.Canale;
import com.example.demomonitor.domain.TV;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/tv")
public class TVController {

    @GetMapping
    public ResponseEntity<TV> returnTv(){
        TV televisore = new TV("Blu", "Samsung", new ArrayList<Canale>(),
                new HashMap<String, String>(), LocalTime.of(9, 0));
        return new ResponseEntity<>(televisore, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TV> createTv(@RequestBody TV televisore){
        return new ResponseEntity<>(televisore, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TV> addCh(@RequestBody Canale canale){
        TV televisore = new TV("Blu", "Samsung", new ArrayList<Canale>(),
                new HashMap<String, String>(), LocalTime.of(9, 0));
        televisore.addChannel(canale);
        return new ResponseEntity<>(televisore, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<TV> addRem(@RequestBody String nome){
        ArrayList<Canale> lista = new ArrayList<Canale>();
        lista.add(new Canale("Rai 1", 1, true));
        lista.add(new Canale("Rai 2", 2, false));
        TV televisore = new TV("Blu", "Samsung", lista,
                new HashMap<String, String>(), LocalTime.of(9, 0));
        televisore.removeChannel(nome);
        return new ResponseEntity<>(televisore, HttpStatus.OK);
    }

    @PutMapping("/ris")
    public ResponseEntity<TV> reconnect(){
        ArrayList<Canale> lista = new ArrayList<Canale>();
        lista.add(new Canale("Italia 1", 6, false));
        lista.add(new Canale("Tv 8", 8, true));
        TV televisore = new TV("Blu", "Samsung", lista,
                new HashMap<String, String>(), LocalTime.of(9, 0));

        televisore.risintonizza();
        return new ResponseEntity<>(televisore, HttpStatus.OK);
    }


}
