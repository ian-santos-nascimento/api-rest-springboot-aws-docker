package br.com.apirestfull.apigateway.controller;

import br.com.apirestfull.apigateway.data.vo.PersonVO;
import br.com.apirestfull.apigateway.data.vov2.PersonVOV2;
import br.com.apirestfull.apigateway.model.Person;
import br.com.apirestfull.apigateway.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll(){
        return personService.findAll();
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person){
        return personService.create(person);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,value = "/v2")
    public PersonVOV2 create(@RequestBody PersonVOV2 person){
        return personService.create(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO person){
        return personService.update(person);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
