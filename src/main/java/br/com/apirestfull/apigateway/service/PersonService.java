package br.com.apirestfull.apigateway.service;

import br.com.apirestfull.apigateway.data.vo.PersonVO;
import br.com.apirestfull.apigateway.data.vov2.PersonVOV2;
import br.com.apirestfull.apigateway.exceptionHandler.ResourceNotFoundException;
import br.com.apirestfull.apigateway.mapper.DozerMapper;
import br.com.apirestfull.apigateway.model.Person;
import br.com.apirestfull.apigateway.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapperService mapperService;

    public PersonVO create(PersonVO personVO) {
        var person = DozerMapper.parseObject(personVO, Person.class);
        var entidade = personRepository.save(person);
        return DozerMapper.parseObject(entidade,PersonVO.class);
    }

    public PersonVOV2 create(PersonVOV2 personVO2) {
        var person = mapperService.convertVOToEntity(personVO2);
        var entidade = personRepository.save(person);
        return  mapperService.convertEntityToVO(entidade);
    }

    public PersonVO update(PersonVO personVO) {
        Person person = personRepository.findById(personVO.getId()).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + personVO.getId()));
        var entidade = personRepository.save(person);
        return DozerMapper.parseObject(entidade, PersonVO.class);
    }

    public PersonVO findById(Long id) {
        var entidade = personRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + id));
        return DozerMapper.parseObject(entidade, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        return DozerMapper.parseListObjects(personRepository.findAll(),PersonVO.class);
    }

    public void delete(Long id) {
        Person entity = personRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("No records found for this ID:" + id));

        personRepository.delete(entity);
    }


}
