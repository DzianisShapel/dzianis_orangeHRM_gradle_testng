package eu.senla.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Employee {

    private String firstName;
    private String middleName;
    private String lastName;
}
