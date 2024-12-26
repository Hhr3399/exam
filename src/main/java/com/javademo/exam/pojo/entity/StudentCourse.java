package com.javademo.exam.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourse {
    private int id;
    private int sId;
    private int cId;
    private int score;
    private LocalDateTime stime;
    private LocalDateTime etime;
}
