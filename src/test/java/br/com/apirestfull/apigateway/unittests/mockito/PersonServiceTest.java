package br.com.apirestfull.apigateway.unittests.mockito;

import br.com.apirestfull.apigateway.data.vo.PersonVO;
import static org.junit.jupiter.api.Assertions.*;

import br.com.apirestfull.apigateway.exceptionHandler.RequiredObjectNullException;
import br.com.apirestfull.apigateway.model.Person;
import br.com.apirestfull.apigateway.repository.PersonRepository;
import br.com.apirestfull.apigateway.service.PersonService;
import br.com.apirestfull.apigateway.unittests.mapper.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testFindById(){
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = service.findById(1L);
        assert(result.getKey() != null);
        assert(!result.getLinks().isEmpty());
        assert(result.toString().contains(""));
    }

    @Test
    void testCreate(){
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L); //Simula salvamento no BD
        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);
        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(vo);
        assert(result.getKey() != null);
        assert(!result.getLinks().isEmpty());
        assert(result.toString().contains(""));
    }

    @Test
    void testCreateNull(){
       Exception exception = assertThrows(RequiredObjectNullException.class,()->{
           service.create(null);
       });
       String expectedMessage ="Null object it is not allowed to be saved";
       String returnedMessage = exception.getMessage();
       assertTrue(returnedMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateNull(){
        Exception exception = assertThrows(RequiredObjectNullException.class,()->{
            service.update(null);
        });
        String expectedMessage ="Null object it is not allowed to be saved";
        String returnedMessage = exception.getMessage();
        assertTrue(returnedMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll(){
        List<Person> personList = input.mockEntityList();
        when(repository.findAll()).thenReturn((personList));
        var result = service.findAll();
        assertNotNull(result);
        assertEquals(14, result.size());
        var personOne = result.get(0);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        assertEquals("Addres Test0",personOne.getAddress());
    }


    @Test
    void testUpdate(){
        Person person = input.mockEntity(1);
        person.setId(1L);
        Person persisted = person;
        persisted.setId(1L); //Simula salvamento no BD
        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(persisted));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(vo);
        assert(result.getKey() != null);
        assert(!result.getLinks().isEmpty());
        assert(result.toString().contains(""));
    }

}
