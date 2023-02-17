package com.ntt.demo0.controller;

import com.ntt.demo0.domain.Gruppo;
import com.ntt.demo0.domain.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gruppo")
public class GruppoController {
    @GetMapping("")
    public ResponseEntity<?> returnGroup(){
        Gruppo group = new Gruppo();
        List<Persona> people = new ArrayList<Persona>();
        people.add(new Persona(1, "marco", 22));
        people.add(new Persona(2, "elena", 19));
        people.add(new Persona(3, "maria", 54));
        group.setPeople(people);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createPersona(@RequestBody Gruppo group){
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }
}
