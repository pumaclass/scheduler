package com.project.dto;
import com.project.entity.Scheduler;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    private String schedule;
    private String name;
    private String pw;
    private LocalDate date;
    private LocalDate editDate;


    public ScheduleResponseDto(Scheduler scheduler) {
        this.id = scheduler.getId();
        this.schedule = scheduler.getSchedule();
        this.name = scheduler.getName();
        this.pw = scheduler.getPw();
        this.date = scheduler.getDate();
        this.editDate = scheduler.getEditDate();
    }

    public ScheduleResponseDto(Long id, String name, String schedule, LocalDate date, String pw, LocalDate editDate) {
        this.id = id;
        this.schedule = schedule;
        this.name = name;
        this.pw = pw;
        this.date = date;
        this.editDate = editDate;
    }
}
