package com.project.dto;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String Schedule;
    private String name;
    private String pw;
    private String date;

    public ScheduleResponseDto() {
        this.id = id;
        this.Schedule = Schedule;
        this.name = name;
        this.pw = pw;
        this.date = date;
    }

    public ScheduleResponseDto(Long id, String name, String schedule, String date, String pw) {
    }
}
