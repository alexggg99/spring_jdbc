package jdbc.dao;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.ResultSetExtractor;
import jdbc.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAOImpl implements PersonDAO {

    private JdbcTemplate jdbcTemplate;

    public PersonDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(Person person) {
        if (person.getId() > 0) {
            // update
            String sql = "UPDATE person SET username=?, email=?, surname=? WHERE id=?";
            jdbcTemplate.update(sql, person.getUsername(), person.getEmail(), person.getSurname(), person.getId());
        } else {
            // insert
            String sql = "INSERT INTO person VALUES (PERSON_SEQ.nextval, ?, ?, ?)";
            jdbcTemplate.update(sql, person.getUsername(), person.getEmail(), person.getSurname());
        }

    }


    @Override
    public Person get(String username) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE LOWER(username) = ?", new Object[]{username.trim().toLowerCase()},
                new ResultSetExtractor<Person>() {
                    @Override
                    public Person extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        if (resultSet.next()) {
                            Person person = new Person();
                            person.setId(resultSet.getInt("id"));
                            person.setUsername(resultSet.getString("username"));
                            person.setEmail(resultSet.getString("email"));
                            person.setSurname(resultSet.getString("surname"));
                            return person;
                        }
                        return null;
                    }
                });
    }
}
