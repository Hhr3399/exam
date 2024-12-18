package com.javademo.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourse {
    private Integer id;
    private Integer sId;
    private Integer cId;
    private Integer score;
    private LocalDateTime stime;
    private LocalDateTime etime;
}
