package com.javademo.exam.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stuuser {
    private int id;
    private String username;
    private String password;
    private String studentName;
    private String gender;
    private String phonenumber;
    private String sid;
    private int collegeId;

}
