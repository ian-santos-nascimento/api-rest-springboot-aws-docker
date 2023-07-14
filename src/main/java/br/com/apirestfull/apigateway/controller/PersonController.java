package br.com.apirestfull.apigateway.controller;

import br.com.apirestfull.apigateway.data.vo.PersonVO;
import br.com.apirestfull.apigateway.data.vov2.PersonVOV2;
import br.com.apirestfull.apigateway.model.Person;
import br.com.apirestfull.apigateway.service.PersonService;
import br.com.apirestfull.apigateway.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v1")
@Tag(name = "People", description = "endpoint for People")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML })
    @Operation(summary = "find all People",description = "find all People",tags = {"People"}, responses = {
            @ApiResponse(description = "success",responseCode = "200",content = {
                    @Content(mediaType = MediaType.MEDIA_TYPE_JSON,array = @ArraySchema(schema = @Schema(implementation = Person.class)))}),
            @ApiResponse(description = "Bad Request",responseCode = "400",content = {@Content}),
            @ApiResponse(description = "Unauthorized",responseCode = "401",content = {@Content}),
            @ApiResponse(description = "Not Found",responseCode = "404",content = {@Content}),
            @ApiResponse(description = "Internal Server Error",responseCode = "500",content = {@Content}),
    })
    public List<PersonVO> findAll(){
        return personService.findAll();
    }

    @GetMapping(path = "{id}", produces = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML })
    @Operation(summary = "find a Person",description = "find a Person",tags = {"Person"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.MEDIA_TYPE_JSON, schema = @Schema(implementation = Person.class))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @PostMapping(consumes = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML },
            produces = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML })
    @Operation(summary = "Create a People",description = "Create a Person",tags = {"Person"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.MEDIA_TYPE_JSON, schema = @Schema(implementation = Person.class))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public PersonVO create(@RequestBody PersonVO person){
        return personService.create(person);
    }

    @PutMapping(consumes = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML },
            produces = {MediaType.MEDIA_TYPE_JSON, MediaType.MEDIA_TYPE_XML,MediaType.MEDIA_TYPE_YAML })
    @Operation(summary = "Update a Person",description = "Update a Person",tags = {"Person"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.MEDIA_TYPE_JSON, schema = @Schema(implementation = Person.class))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public PersonVO update(@RequestBody PersonVO person){
        return personService.update(person);
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete a person",description = "Delete a Person",tags = {"Person"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
