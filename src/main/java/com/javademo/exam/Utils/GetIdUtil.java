package com.javademo.exam.Utils;

import com.javademo.exam.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetIdUtil {
    @Autowired
    private static JwtProperties jwtProperties;
    public static Integer getId(HttpServletRequest req){
        String token = req.getHeader("token");
        Integer id = (Integer) JwtUtils.parseJWT(jwtProperties.getSecretKey(), token).get("id");
        return id;
    }
}
