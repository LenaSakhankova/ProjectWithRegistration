package com.iba.springsecurity.dao;



import com.iba.springsecurity.models.Book;
import com.iba.springsecurity.models.Reader;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(com.iba.springsecurity.models.@Valid Book book) {
        jdbcTemplate.update("INSERT INTO book(name,personId, year, shortDescription, " +
                "storageLocation, tom)" + " VALUES(?, ?, ?, ?, ?, ?)",
                book.getName(), book.getPersonId(), book.getYear(), book.getShortDescription(), book.getStorageLocation(),
                book.getTom());
    }

    public void update(int id, com.iba.springsecurity.models.@Valid Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET name=?, Year=?, shortDescription=?, storageLocation=?, tom=?" +
                        " WHERE id=?", updatedBook.getName(),
                updatedBook.getYear(), updatedBook.getShortDescription(), updatedBook.getStorageLocation(),
                updatedBook.getTom(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM debtors WHERE book_id=?", id);
        jdbcTemplate.update("DELETE FROM author WHERE book_id=?", id);
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Optional<Reader> getBookOwner(int id)
    {
        return jdbcTemplate.query("Select reader.* from Book join Reader on book.personId = reader.id" +
                        " where book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Reader.class))
                .stream().findAny();
    }
//освободить книгу у человека(он вернул её)
    public void release(int id)
    {
        jdbcTemplate.update("update book set personId = null where id=?", id);
    }
    public void assign(int id, Reader selectedReader)
    {
        jdbcTemplate.update("update book set personId = ? where id=?", selectedReader.getId(), id);
    }

    public List<Book> bookInUse()
    {
        return jdbcTemplate.query("select * from den.book where personID is not null;", new BeanPropertyRowMapper<>(Book.class));
    }
}
