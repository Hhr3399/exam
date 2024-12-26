package com.javademo.exam.interceptor;

import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.pojo.entity.Result;
import com.javademo.exam.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    @Override //目标资源方法运行前运行, 返回true: 放行, 放回false, 不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            return true;
        }
        //1.获取请求url。
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}",url);

        //2.判断请求url中是否包含login和register，如果包含，说明是登录操作，放行。
        if(url.contains("login")){
            log.info("登录操作, 放行...");
            return true;
        } else if (url.contains("register")) {
            log.info("注册操作，放行");
            return true;
            
        }
        String token = req.getHeader("token");
        try {
            Claims claims = JwtUtils.parseJWT(jwtProperties.getSecretKey(), token);
            Long id = Long.valueOf(claims.get("id").toString());
            //通过，放行
            return true;
        } catch (Exception ex) {
            //不通过，响应401状态码,表示为登录
            response.setStatus(401);
            Result.error("未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401状态码
            response.setContentType("application/json; charset=UTF-8"); // 设置响应的内容类型和字符编码
            response.setCharacterEncoding("UTF-8"); // 显式设置字符编码（虽然content-type已经包含了）

            try (PrintWriter out = response.getWriter()) {
                // 写入JSON格式的错误信息
                // 注意："{未登录}" 不是一个有效的JSON对象，这里我改为一个有效的JSON对象
                out.print("{\"error\":\"未登录，请提供有效的认证信息\"}");
            }
            return false;
        }
    }

    @Override //目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ...");
    }

    @Override //视图渲染完毕后运行, 最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
