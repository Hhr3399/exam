package com.javademo.exam.Utils;

import com.javademo.exam.common.JwtClaimsConstant;
import com.javademo.exam.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetIdUtil {
    @Autowired
    private static JwtProperties jwtProperties;
    public static String getId(HttpServletRequest req){
        String token = req.getHeader("token");
        //Integer userid = (Integer) claims.get(JwtClaimsConstant.USER_ID);
        String uid =  (String) JwtUtils.parseJWT(jwtProperties.getSecretKey(), token).get(JwtClaimsConstant.USER_ID);
        return uid;
    }
}
