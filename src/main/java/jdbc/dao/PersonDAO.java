package jdbc.dao;

import jdbc.domain.Person;

public interface PersonDAO {
    void saveOrUpdate(Person person);

    Person get(String username);
}
