package com.project.repository;

import com.project.dto.ScheduleResponseDto;
import com.project.dto.SchedulerRequestDto;
import com.project.entity.Scheduler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Scheduler save(Scheduler schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (name, schedule, date, pw) VALUES (?, ?, ? ,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, schedule.getName());
            ps.setString(2, schedule.getSchedule());
            ps.setObject(3, schedule.getDate());
            ps.setString(4, schedule.getPw());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String schedule = rs.getString("schedule");
                LocalDate date = rs.getDate("date").toLocalDate();
                String pw = rs.getString("pw");
                LocalDate editDate = rs.getDate("date").toLocalDate();
                return new ScheduleResponseDto(id, name, schedule, date, pw, editDate);
            }
        });
    }

    public void update(Long id, SchedulerRequestDto requestDto) {
        String sql = "UPDATE schedule SET name = ?, schedule = ?, editDate = ?, pw = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getName(), requestDto.getSchedule(),
                requestDto.getDate(), requestDto.getPw(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Scheduler findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Scheduler scheduler = new Scheduler();
                scheduler.setId(resultSet.getLong("id"));
                scheduler.setName(resultSet.getString("name"));
                scheduler.setSchedule(resultSet.getString("schedule"));
                return scheduler;
            } else {
                return null;
            }
        }, id);
    }
}
