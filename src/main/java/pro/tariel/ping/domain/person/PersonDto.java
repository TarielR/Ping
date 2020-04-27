package pro.tariel.ping.domain.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String spouseName;
    private List<Person> children;
    private Address address;

    public PersonDto(Person p, String spouseName) {
        this.id = p.getId();
        this.name = p.getName();
        this.dateOfBirth = p.getDateOfBirth();
        this.spouseName = spouseName;
        this.children = p.getChildren();
        this.address = p.getAddress();
    }
}
