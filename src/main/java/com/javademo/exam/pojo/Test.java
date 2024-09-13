package com.javademo.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Column(name = "student_name")
    private  String studentName;
    @Column(name = "course_name")
    private  String courseName;
    private String status;
    private Integer score;

}
