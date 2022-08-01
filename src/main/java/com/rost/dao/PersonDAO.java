package com.rost.dao;

import com.rost.config.SpringConfig;
import com.rost.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final BeanPropertyRowMapper<Person> personMapper = new BeanPropertyRowMapper<>(Person.class);

    public int getCurrentMaxId() {
        return jdbcTemplate.queryForObject("SELECT max(id) FROM person", Integer.class);
    }

    public List<Person> readPeople() {
        return jdbcTemplate.query("SELECT id, first_name, last_name, patronymic, birth_year FROM person", personMapper);
    }

    public Person readPersonById(int id) {
        return jdbcTemplate.query("SELECT id, first_name, last_name, patronymic, birth_year FROM person WHERE id = ?", new Object[]{id}, personMapper).stream()
                .findAny()
                .orElse(null);
    }

    public void createPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(first_name, last_name, patronymic, birth_year) VALUES (?, ?, ?, ?)",
                person.getFirstName(), person.getLastName(), person.getPatronymic(), person.getBirthYear());
    }

    public void updatePerson(Person person) {
        jdbcTemplate.update("UPDATE person SET first_name = ?, last_name = ?, patronymic = ?, birth_year = ? WHERE ID = ?",
                person.getFirstName(), person.getLastName(), person.getPatronymic(), person.getBirthYear(), person.getId());
    }
    //main-method test
}
