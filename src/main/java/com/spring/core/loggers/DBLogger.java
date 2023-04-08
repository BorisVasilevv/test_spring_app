package com.spring.core.loggers;


import com.spring.core.beans.Event;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBLogger implements EventLogger{

    JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public String getName() {
        return "DBLogger";
    }

    @Override
    public void logEvent(Event event) {

        jdbcTemplate.update("INSERT INTO events (id, date, msg) VALUES (?,?,?)",
                event.getId(),event.getDate(),event.getMessage());
    }
}
