package com.project.entity;

import com.project.dto.SchedulerRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Scheduler {
    private Long id;
    private String schedule;
    private String name;
    private String pw;
    private LocalDate date;
    private LocalDate editDate;

    public Scheduler(SchedulerRequestDto requestDto) {
        this.schedule = requestDto.getSchedule();
        this.name = requestDto.getName();
        this.pw = requestDto.getPw();
        this.date = requestDto.getDate();
        this.editDate = requestDto.getEditDate();
    }

    public void update(SchedulerRequestDto requestDto) {
        this.schedule = requestDto.getSchedule();
        this.name = requestDto.getName();
        this.pw = requestDto.getPw();
        this.date = requestDto.getDate();
        this.editDate = requestDto.getEditDate();
    }
}
