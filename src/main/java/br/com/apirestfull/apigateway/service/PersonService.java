package br.com.apirestfull.apigateway.service;

import br.com.apirestfull.apigateway.exceptionHandler.ResourceNotFoundException;
import br.com.apirestfull.apigateway.model.Person;
import br.com.apirestfull.apigateway.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) {
        Person entity = personRepository.findById(person.getId()).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + person.getId()));
        return personRepository.save(entity);
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + id));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void delete(Long id) {
        Person entity = personRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + id));

        personRepository.delete(entity);
    }


}
