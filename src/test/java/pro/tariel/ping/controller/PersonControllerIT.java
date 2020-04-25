package pro.tariel.ping.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pro.tariel.ping.domain.person.Person;
import pro.tariel.ping.service.PersonService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void allPersonsShouldReturnMessageFromService() throws Exception {
        String uri = "/persons";
        when(service.getAllPersons()).thenReturn(List.of(new Person()));
        this.mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(service, times(1)).getAllPersons();
        verifyNoMoreInteractions(service);
    }

    @Test
    void onePersonShouldReturnMessageFromService() throws Exception {
        String uri = "/person/1";
        when(service.getPersonById(7L)).thenReturn(new Person());
        this.mockMvc.perform(get(uri))
                .andExpect(status().isOk());
        verify(service, times(1)).getPersonById(anyLong());
        verifyNoMoreInteractions(service);
    }

    @Test
    void retrieveAllPersonsIT() {
        String uri = "/persons";

    }

    @Test
    void retrievePersonByIdIT() {
    }
}
