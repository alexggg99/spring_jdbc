package spring_jdbc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_jdbc.dao.PersonDAO;
import spring_jdbc.domain.Person;


/**
 * Created by Alexey_Gashchuk on 28-08-18.
 */
public class SpringApp {

    public static void main(String[] args) {

        AbstractApplicationContext context = (AbstractApplicationContext) new ClassPathXmlApplicationContext("beans.xml");

        PersonDAO dao = (PersonDAO) context.getBean("personDao");

        // Show Product object
        dao.get("alexggg99");

        // Insert operator
        Person person = new Person();
        person.setUsername("test898");
        person.setEmail("testEmail");
        person.setSurname("sur");
        dao.saveOrUpdate(person);
        System.out.println(dao.get("test898"));

    }
}
