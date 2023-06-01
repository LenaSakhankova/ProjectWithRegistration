package com.iba.springsecurity.dao;

import com.iba.springsecurity.models.Librarian;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class LibrarianDAO {
    private final JdbcTemplate jdbcTemplate;

    public LibrarianDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Librarian> index() {
        return jdbcTemplate.query("SELECT * FROM librarian", new BeanPropertyRowMapper<>(Librarian.class));
    }

    public Librarian show(int id) {
        Librarian librarian = jdbcTemplate.query("SELECT * FROM librarian WHERE Librarian_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Librarian.class))
                .stream().findAny().orElse(null);
        return librarian;
    }

    public void save(Librarian librarian) {
        jdbcTemplate.update("INSERT INTO Librarian(Last_name, First_name, Patronymic, Phone, Address) VALUES(?, ?, ?, ?, ?)",
                librarian.getLast_name(), librarian.getFirst_name(), librarian.getPatronymic(),
                librarian.getPhone(), librarian.getAddress());
    }

    public void update(int id, Librarian librarian) {
        jdbcTemplate.update("UPDATE librarian SET Last_name=?, First_name=? patronymic=? phone=? address=? WHERE " +
                        "Librarian_id=?", librarian.getLast_name(), librarian.getFirst_name(), librarian.getPatronymic(),
                librarian.getPhone(), librarian.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM librarian WHERE Librarian_id =?", id);
    }

    public Optional<Librarian> getPersonByFIO(String last){
        return jdbcTemplate.query("Select * from Librarian where last_name=?",
                new Object[]{last},
                new BeanPropertyRowMapper<>(Librarian.class)).stream().findAny();
    }
}
