package com.javademo.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;
    private String content;
    private boolean answer;
    private Integer singleScore;
    private Integer courseId;
    private String courseName;

}
