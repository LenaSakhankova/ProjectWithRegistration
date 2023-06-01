package com.iba.springsecurity.dao;

import com.iba.springsecurity.models.Book;
import com.iba.springsecurity.models.Debtor;
import com.iba.springsecurity.models.Reader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DebtorsDAO {
    private final JdbcTemplate jdbcTemplate;

    public DebtorsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(){
        String sql = "INSERT INTO den.debtors (book_id, person_id) " +
                "SELECT b.id, b.personId " +
                "FROM den.book b " +
                "WHERE b.personId IS NOT NULL " +
                "AND NOT EXISTS (SELECT 1 FROM den.debtors d " +
                "WHERE d.book_id = b.id AND d.person_id = b.personId)";
        jdbcTemplate.update(sql);
    }
    public List<Debtor> index() {
        add();
        return jdbcTemplate.query("select * from debtors", new BeanPropertyRowMapper<>(Debtor.class));
    }
    public List<Reader> read(){
            List<Reader> readers = jdbcTemplate.query("SELECT reader.* FROM den.debtors JOIN den.reader ON " +
                            "debtors.person_id = reader.id", new BeanPropertyRowMapper<>(Reader.class));

            return readers;
        }

    public List<Book> book(){
        List<Book> books = jdbcTemplate.query("SELECT book.* FROM den.debtors JOIN den.book ON " +
                "debtors.book_id = book.id", new BeanPropertyRowMapper<>(Book.class));

        return books;
    }
}
