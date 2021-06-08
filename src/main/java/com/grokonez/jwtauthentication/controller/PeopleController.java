package com.grokonez.jwtauthentication.controller;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grokonez.jwtauthentication.exception.ResourceNotFoundException;
import com.grokonez.jwtauthentication.model.People;
import com.grokonez.jwtauthentication.repository.PeopleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

/*
– @CrossOrigin is for configuring allowed origins.
– @RestController annotation is used to define a controller and to indicate that the return value of the methods should be be bound to the web response body.
– @RequestMapping("/api") declares that all Apis’ url in the controller will start with /api.
– We use @Autowired to inject TutorialRepository bean to local variable.

You can continue with step by step to implement this Spring Boot Server in the post:
Spring Boot Rest CRUD API with Spring Data JPA & MySQL
*/
@CrossOrigin()
@RestController
@RequestMapping("/api/auth")

public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;

    //get all
    @GetMapping("/people")
    public Page<People> getAllPeople(Pageable pageable) {
        return peopleRepository.findAll(pageable);
    }

    //create
    @PostMapping("people")
    public People createPeople(@RequestBody People people){
        return peopleRepository.save(people);
    }

    //get by id
    @GetMapping("/people/{id}")
    public ResponseEntity < People > getPeopleById(@PathVariable Long id) {
        People people = peopleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("People not found id :" + id));
        return ResponseEntity.ok(people);
    }

    //update 
    @PutMapping("/people/{id}")
    public ResponseEntity < People > updatePeople(@PathVariable Long id, @RequestBody People peopleDetails) {
        People people = peopleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("People not found id :" + id));

        people.setName(peopleDetails.getName());
        people.setEmail(peopleDetails.getEmail());
        people.setAddress(peopleDetails.getAddress());
        people.setPhone(peopleDetails.getPhone());

        People updatedPeople = peopleRepository.save(people);
        return ResponseEntity.ok(updatedPeople);
    }

    //delete
    @DeleteMapping("/people/{id}")
    public ResponseEntity < Map < String, Boolean >> deletePeople(@PathVariable Long id) {
        People people = peopleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("People not found id :" + id));

        peopleRepository.delete(people);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    /*
    @GetMapping("/people")
    public ResponseEntity<List<People>> getAllPeople(@RequestParam(required = false) String name){
        try{
            List<People> peoples = new ArrayList<People>();

            if(name == null)
                peopleRepository.findAll().forEach(peoples::add);
            else
                peopleRepository.findByNameContaining(name).forEach(peoples::add);
            
            if(peoples.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(peoples, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }*/
    /*
    @GetMapping("/peopleget/{id}")
    public ResponseEntity<People> getPeopleById(@PathVariable("id") long id){
            Optional<People> peopleData = peopleRepository.findById(id);

            if(peopleData.isPresent()){
                return new ResponseEntity<>(peopleData.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping("/peoplepost")
    public ResponseEntity<People> createPeople(@RequestBody People people){
        try {
            People _people = peopleRepository
    .save(new People(people.getName(),people.getEmail(),people.getAddress(),people.getPhone()));
            return new ResponseEntity<>(_people, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/peopleput/{id}")
    public ResponseEntity<People> updatePeople(@PathVariable("id") long id, @RequestBody People people){
        Optional<People> peopleData = peopleRepository.findById(id);

            if(peopleData.isPresent()){
                People _people = peopleData.get();
                _people.setName(people.getName());
                _people.setEmail(people.getEmail());
                _people.setAddress(people.getAddress());
                _people.setPhone(people.getPhone());
                return new ResponseEntity<>(peopleRepository.save(_people), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @DeleteMapping("/peopledel")
    public ResponseEntity<HttpStatus> deleteAllPeople(){
        try {
            peopleRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/peopleget/email")
    public ResponseEntity<List<People>> getAllEmail(@RequestParam(required = false) String email){
        try{
            List<People> peoples = new ArrayList<People>();

            if(email == null)
                peopleRepository.findAll().forEach(peoples::add);
            else
                peopleRepository.findByEmailContaining(email).forEach(peoples::add);
            
            if(peoples.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(peoples, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }*/

}