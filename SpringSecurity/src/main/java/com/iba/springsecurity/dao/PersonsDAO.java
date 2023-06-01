package com.iba.springsecurity.dao;


import com.iba.springsecurity.models.Book;
import com.iba.springsecurity.models.Reader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonsDAO {
    private final JdbcTemplate jdbcTemplate;
    public PersonsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reader> index() {
        return jdbcTemplate.query("SELECT * FROM Reader", new BeanPropertyRowMapper<>(Reader.class));
    }

    public Reader show(int id) {
        return jdbcTemplate.query("SELECT * FROM Reader WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Reader.class))
                .stream().findAny().orElse(null);
    }

    public void save(Reader reader) {
        jdbcTemplate.update("INSERT INTO Reader(FIO, yearOfBirth, phone_number) VALUES(?, ?, ?)",
                reader.getFIO(), reader.getYearOfBirth(), reader.getPhone_number());
    }

    public void update(int id, Reader updatedReader) {
        jdbcTemplate.update("UPDATE Reader SET FIO=?, yearOfBirth=? WHERE id=?", updatedReader.getFIO(),
                updatedReader.getYearOfBirth(), id);
    }

    public void delete(int id)
    {
        jdbcTemplate.update("Delete from debtors where Person_id = ?", id);
        jdbcTemplate.update("DELETE FROM Reader WHERE id=?", id);
    }

    public Optional<Reader> getPersonByFIO(String FIO){
        return jdbcTemplate.query("Select * from Reader where FIO=?", new Object[]{FIO},
                new BeanPropertyRowMapper<>(Reader.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("Select * from book where personId=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

}
