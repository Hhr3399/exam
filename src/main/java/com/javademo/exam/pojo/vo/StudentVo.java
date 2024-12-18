package com.javademo.exam.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentVo {
    private Integer id;
    private String studentName;
    private String sid;
    private String collegeName;
}
