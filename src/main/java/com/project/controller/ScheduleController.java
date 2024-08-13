package com.project.controller;

import com.project.dto.ScheduleResponseDto;
import com.project.dto.SchedulerRequestDto;
import com.project.entity.Scheduler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody SchedulerRequestDto requestDto) {
        Scheduler schedule = new Scheduler(requestDto);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schdule (NAME, SCHEDULE, DATE, PW) VALUES (?, ?, ? ,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, requestDto.getName());
            ps.setString(2, requestDto.getSchedule());
            ps.setString(3, requestDto.getDate());
            ps.setString(4, requestDto.getPw());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        ScheduleResponseDto responseDto = new ScheduleResponseDto();
        return responseDto;
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules() {
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String schedule = rs.getString("schedule");
                String date = rs.getString("date");
                String pw = rs.getString("pw");
                return new ScheduleResponseDto(id, name, schedule, date, pw);
            }
        });
    }

}
