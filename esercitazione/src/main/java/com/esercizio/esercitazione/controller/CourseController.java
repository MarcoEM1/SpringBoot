package com.esercizio.esercitazione.controller;

import com.esercizio.esercitazione.DuplicateKeyException;
import com.esercizio.esercitazione.Errore;
import com.esercizio.esercitazione.MissingArgumentsException;
import com.esercizio.esercitazione.NoContentException;
import com.esercizio.esercitazione.domain.Course;
import com.esercizio.esercitazione.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @PostMapping()
    public ResponseEntity<?> createCourse(@RequestBody Course corso){
        HashMap<String, String> mappaCorsi = new HashMap<String, String>();
        List<Course> corsi = getListCorsi();
        for(Course cor : corsi){
            mappaCorsi.put(cor.getNome(), cor.getDescrizione());
        }
        try{
            if(corso.getNome() == null || corso.getDescrizione() == null){
                throw new MissingArgumentsException("Nome o descrizione vuoti, " +
                        "inserire tutti i campi!");
            }
            if(!(corso.getNome().substring(0, 6).equals("corso_") && corso.getNome().substring(6).length() >= 8)){
                throw new NoContentException("No content.");
            }
            if(mappaCorsi.containsKey(corso.getNome())){
                throw new DuplicateKeyException("Error duplicate");
            }

            Course corso1 = courseRepository.save(corso);
            return new ResponseEntity<>(corso1, HttpStatus.CREATED);
        } catch (DuplicateKeyException | MissingArgumentsException | IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Errore(e.getMessage()), HttpStatus.OK);
        }
        catch (NoContentException nce){
            System.out.println(nce.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(getListCorsi(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Course>> deleteCourse(@PathVariable long id){
        Optional<Course> corso = courseRepository.findById(id);
        courseRepository.deleteById(id);
        return new ResponseEntity<>(corso, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable long id, @RequestBody Course corso){
        Optional<Course> corsoPresente = courseRepository.findById(id);
        if(corsoPresente.isPresent()){
            corso.setId(corsoPresente.get().getId());
            courseRepository.save(corso);
        }
        return new ResponseEntity<>(corso, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Course>> getCourse(@PathVariable long id){
        Optional<Course> corso1 = courseRepository.findById(id);
        return new ResponseEntity<>(corso1, HttpStatus.OK);
    }

    @GetMapping("/num")
    public ResponseEntity<Integer> numCourses(){
        int l = getListCorsi().toArray().length;
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/undnum")
    public ResponseEntity<Integer> numUndCourses(){
        List<Course> corsiEsistenti = getListCorsi();
        int c = 0;
        for(Course cor : corsiEsistenti){
            if(cor.getNome().substring(0, 6).equals("corso_")){
                c += 1;
            }
        }
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/descr")
    public ResponseEntity<List<Course>> numDescr20(){
        ArrayList<Course> listacor = new ArrayList<Course>();
        for(Course cor : getListCorsi()){
            if(cor.getDescrizione().length() < 20){
                listacor.add(cor);
            }
        }

        return new ResponseEntity<>(listacor, HttpStatus.OK);
    }

    public List<Course> getListCorsi(){
        return courseRepository.findAll().stream().toList();
    }


}
