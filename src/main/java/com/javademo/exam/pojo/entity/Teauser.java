package com.javademo.exam.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teauser {
    private int id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phonenumber;
    private String tid;
    private int collegeId;
    private int courseId;
    private String collegeName;
    private String courseName;
}
