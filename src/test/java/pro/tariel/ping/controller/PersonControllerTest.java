package pro.tariel.ping.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.tariel.ping.domain.person.Address;
import pro.tariel.ping.domain.person.Person;
import pro.tariel.ping.domain.person.PersonDto;
import pro.tariel.ping.service.PersonService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @InjectMocks
    private PersonController controller;

    @Mock
    private PersonService service;

    private List<Person> persons;
    private Person person01;
    private List<PersonDto> personDtoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Address address01 = new Address(1011, "Foster st.", "West Chester", "19380");
        Address address02 = new Address(202, "Grant Ave", "Philadelphia", "01001");

        Person child01 = new Person(1L, "Anni", LocalDate.of(1999, 2, 3), null, null, null);
        Person child02 = new Person(2L, "Tom", LocalDate.of(1997, 5, 31), null, null, address01);

        List<Person> children01 = List.of(child01, child02);

        person01 = new Person(7L, "John Smith", LocalDate.of(1966, 9, 10), 8L, children01, address01);
        Person person02 = new Person(8L, "Yulia Smith", LocalDate.of(1969, 5, 22), 7L, children01, address01);
        Person person03 = new Person(11L, "Don Donalds", LocalDate.of(1977, 1, 10), null, null, address02);

        persons = List.of(person01, person02, person03);
    }

    //@Disabled
    @Test
    void retrieveAllPersons() {
        for (Person p : persons) {
            PersonDto pdto = convertToDto(p);
//            System.out.println(pdto.getName() + "   " + pdto.getSpouseName());
            personDtoList.add(pdto);
        }

        // given
        when(service.getAllPersons()).thenReturn(personDtoList);

        // when
         List<PersonDto> actual = controller.retrieveAllPersons();

        // then
        assertEquals(3, actual.size());
        assertEquals("John Smith", actual.get(0).getName());
        assertEquals("Yulia Smith", actual.get(0).getSpouseName());
        assertEquals("Don Donalds", actual.get(2).getName());
        assertNull(actual.get(2).getSpouseName());
        assertNotNull(actual.get(1).getAddress());
        assertNotNull(actual.get(1).getChildren());
        assertNull(actual.get(2).getChildren());
        assertNull(actual.get(0).getChildren().get(0).getAddress());
        assertNotNull(actual.get(0).getChildren().get(1).getAddress());
    }

    private PersonDto convertToDto(Person p) {
        String spouseName = null;

        if (null != p.getSpouseId()) {
            spouseName = getSpouseName(p);
        }

        return new PersonDto(p, spouseName);
    }

    @Test
    void retrievePersonById() {
        PersonDto personDto = new PersonDto(person01, "Yulia Smith");
        // given
        when(service.getPersonById(anyLong())).thenReturn(personDto);

        // when
        ResponseEntity<PersonDto> actual = controller.retrievePersonById(7L);

        // then
        assertNotNull(actual);
        actual.getStatusCode();
        assertEquals(HttpStatus.OK, HttpStatus.OK);
        assertEquals(person01.getName(), actual.getBody().getName());
        assertNotNull(actual.getBody().getAddress());
    }

    private String getSpouseName(Person person) {

        if(person.getSpouseId() == null)
            return null;

        return persons.stream()
                .filter(a -> a.getId().equals(person.getSpouseId()))
                //.forEach(s -> System.out.println(s.getName()));//
         .findFirst().get().getName();
    }
}
