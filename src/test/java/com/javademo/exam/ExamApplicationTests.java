package com.javademo.exam;

import com.javademo.exam.Utils.BeanUtil;
import com.javademo.exam.pojo.Stuuser;
import com.javademo.exam.service.StuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamApplicationTests {
@Autowired
   private StuService stuService;
    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        Stuuser source = new Stuuser();
        source.setStudentName("张三");
        source.setPhonenumber("123456789");

        Stuuser target = stuService.gets(1); // 数据库获取的对象
        BeanUtil.copyNonNullProperties(source, target);

// 此时 target 的 "studentName" 和 "phonenumber" 已被更新，其它字段保持原样
        stuService.update(target);

    }

}
