package spring_jdbc.dao;

import spring_jdbc.domain.Person;

public interface PersonDAO {
    void saveOrUpdate(Person person);

    Person get(String username);
}
