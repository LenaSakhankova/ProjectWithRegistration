package com.iba.springsecurity.dao;

import com.iba.springsecurity.models.Authors;
import com.iba.springsecurity.models.Publishing;
import com.iba.springsecurity.models.Reader;
import jdk.jfr.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PublishingDAO {
    private final JdbcTemplate jdbcTemplate;

    public PublishingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Publishing> index()
    {
        return jdbcTemplate.query("select * from den.publishing ORDER BY Idpublishing ASC;",
                new BeanPropertyRowMapper<>(Publishing.class));
    }

    public List<Authors> authPublishing(){
        return jdbcTemplate.query("SELECT * FROM den.Author ORDER BY publishing_id ASC;", new BeanPropertyRowMapper<>(Authors.class));
    }

    public Publishing show(int id) {
        return jdbcTemplate.query("SELECT * FROM publishing WHERE Idpublishing=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Publishing.class))
                .stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from author where publishing_id = ?", id);
        jdbcTemplate.update("DELETE FROM publishing WHERE Idpublishing=?", id);
    }
}
