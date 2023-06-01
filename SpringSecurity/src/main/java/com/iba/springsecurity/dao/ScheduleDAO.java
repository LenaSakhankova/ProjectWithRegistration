package com.iba.springsecurity.dao;

import com.iba.springsecurity.models.Reader;
import com.iba.springsecurity.models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleDAO {
    private final JdbcTemplate jdbcTemplate;
    public ScheduleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Schedule> index() {
        return jdbcTemplate.query("SELECT * FROM schedule", new BeanPropertyRowMapper<>(Schedule.class));
    }
    public void create(Schedule schedule){
            jdbcTemplate.update("INSERT INTO schedule(Librarian_id, Work_days, Timetable, Department, Kabinet)" +
                            " VALUES(?, ?, ?, ?, ?)",
                    schedule.getLibrarian_id(), schedule.getWork_days(), schedule.getTimetable(), schedule.getDepartment(),
                    schedule.getKabinet());
    }
}
