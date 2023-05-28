package br.com.apirestfull.apigateway.service;

import br.com.apirestfull.apigateway.controller.PersonController;
import br.com.apirestfull.apigateway.data.vo.PersonVO;
import br.com.apirestfull.apigateway.data.vov2.PersonVOV2;
import br.com.apirestfull.apigateway.exceptionHandler.RequiredObjectNullException;
import br.com.apirestfull.apigateway.exceptionHandler.ResourceNotFoundException;
import br.com.apirestfull.apigateway.mapper.DozerMapper;
import br.com.apirestfull.apigateway.model.Person;
import br.com.apirestfull.apigateway.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapperService mapperService;

    public PersonVO create(PersonVO personVO) {
        if(personVO == null)
            throw new RequiredObjectNullException();
        var person = DozerMapper.parseObject(personVO, Person.class);
        var entidade = personRepository.save(person);
        PersonVO vo= DozerMapper.parseObject(entidade, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }


    public PersonVO update(PersonVO personVO) {
        if(personVO == null)
            throw new RequiredObjectNullException();
        Person person = personRepository.findById(personVO.getKey()).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + personVO.getKey()));
        var entidade = personRepository.save(person);
        PersonVO vo= DozerMapper.parseObject(entidade, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO findById(Long id) {
        var entidade = personRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + id));
        PersonVO personVO= DozerMapper.parseObject(entidade, PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    public List<PersonVO> findAll() {
        var personList =  DozerMapper.parseListObjects(personRepository.findAll(),PersonVO.class);
        personList.forEach(personVO -> personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel()));
        return personList;
    }

    public void delete(Long id) {
        Person entity = personRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + id));

        personRepository.delete(entity);
    }


}
