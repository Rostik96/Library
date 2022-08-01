package com.rost.dao;

import com.rost.config.SpringConfig;
import com.rost.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final BeanPropertyRowMapper<Person> personMapper = new BeanPropertyRowMapper<>(Person.class);

    public List<Person> readPeople() {
        return jdbcTemplate.query("SELECT id, first_name, last_name, patronymic, birth_year FROM person", personMapper);
    }

    //main-method test
}
