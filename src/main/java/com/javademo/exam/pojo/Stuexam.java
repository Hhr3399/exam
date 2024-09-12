package com.javademo.exam.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stuexam {
    private int score;
    private LocalDateTime stime;
    private LocalDateTime ftime;
    private String student_name;
    private String course_name;
}
