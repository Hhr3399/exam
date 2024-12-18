package com.javademo.exam.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherVo {
    private Integer id;
    private String name;
    private String collegeName;
    private String courseName;
}
