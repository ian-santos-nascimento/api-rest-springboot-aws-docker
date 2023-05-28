package br.com.apirestfull.apigateway.controller;

import br.com.apirestfull.apigateway.data.vo.PersonVO;
import br.com.apirestfull.apigateway.data.vov2.PersonVOV2;
import br.com.apirestfull.apigateway.service.PersonService;
import br.com.apirestfull.apigateway.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML })
    public List<PersonVO> findAll(){
        return personService.findAll();
    }

    @GetMapping(path = "{id}", produces = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML })
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @PostMapping(consumes = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML },
            produces = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML })
    public PersonVO create(@RequestBody PersonVO person){
        return personService.create(person);
    }

    @PutMapping(consumes = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML },
            produces = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML })
    public PersonVO update(@RequestBody PersonVO person){
        return personService.update(person);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
