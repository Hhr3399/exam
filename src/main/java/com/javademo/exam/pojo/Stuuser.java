package com.javademo.exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stuuser {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String phonenumber;
    private String sid;
    private Integer collegeId;
}
