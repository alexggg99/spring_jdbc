package jdbc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import jdbc.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class PersonDaoTest {

    @Autowired
    private PersonDAO personDAO;

    @Test
    public void get() throws Exception {
        Person person = personDAO.get("alexggg99");
        assertThat(person).isNotNull();
        assertThat(person).isExactlyInstanceOf(Person.class);
        assertThat(person.getUsername()).isEqualTo("alexggg99");
    }

    @Test
    public void getNull() throws Exception {
        Person person = personDAO.get("nullObject");
        assertThat(person).isNull();
    }

    @Test
    public void save() throws Exception {
        Person person = new Person();
        person.setUsername("test898");
        person.setEmail("testEmail");
        person.setSurname("testSurname");
        personDAO.saveOrUpdate(person);

        Person personToTest = personDAO.get("test898");
        assertThat(personToTest).isNotNull();
        assertThat(personToTest.getEmail()).isEqualTo("testEmail");
        assertThat(personToTest.getSurname()).isEqualTo("testSurname");
    }

    @Test
    public void update() throws Exception {
        Person person = personDAO.get("alexggg99");
        person.setSurname("newSurname");
        personDAO.saveOrUpdate(person);

        Person personToTest = personDAO.get("alexggg99");
        assertThat(personToTest.getSurname()).isEqualTo("newSurname");
    }

}
