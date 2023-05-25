package br.com.apirestfull.apigateway.service;

import br.com.apirestfull.apigateway.data.vov2.PersonVOV2;
import br.com.apirestfull.apigateway.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapperService {

    public PersonVOV2 convertEntityToVO(Person person){
        PersonVOV2 vo = new PersonVOV2();
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setId(person.getId());
        vo.setBirthDay(new Date());
        return vo;
    }

    public Person convertVOToEntity(PersonVOV2 vo){
        Person person = new Person();
        person.setFirstName(vo.getFirstName());
        person.setLastName(vo.getLastName());
        person.setAddress(vo.getAddress());
        person.setGender(vo.getGender());
        person.setId(vo.getId());
        vo.setBirthDay(new Date());
        return person;
    }
}
