package com.project.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SchedulerRequestDto {
    private String schedule;
    private String name;
    private LocalDate date;
    private LocalDate editDate;
    private String pw;
}
