package spring_jdbc.domain;

import lombok.Data;

@Data
public class Person {

    private long id;
    private String username;
    private String email;
    private String surname;

}
