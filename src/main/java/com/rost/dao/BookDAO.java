package com.rost.dao;

import com.rost.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final BeanPropertyRowMapper<Book> bookMapper = new BeanPropertyRowMapper<>(Book.class);

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
    }

    public List<Book> readBooks() {
        return jdbcTemplate.query("SELECT id, title, author, year FROM book", bookMapper);
    }

    public Book readBookById(int id){
        return jdbcTemplate.query("SELECT id, title, author, year FROM book WHERE id = ?", new Object[]{id}, bookMapper).stream()
                .findAny()
                .orElse(null);
    }

    public void updateBook(Book book) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year = ? WHERE id = ?", book.getTitle(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void deleteBookById(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }
    //Util methods
    public int getCurrentMaxId() {
        return jdbcTemplate.queryForObject("SELECT max(id) FROM book", Integer.class);
    }
}
