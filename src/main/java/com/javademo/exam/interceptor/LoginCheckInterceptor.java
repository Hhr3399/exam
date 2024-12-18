package com.javademo.exam.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.javademo.exam.Utils.JwtUtils;
import com.javademo.exam.common.JwtClaimsConstant;
import com.javademo.exam.pojo.Result;
import com.javademo.exam.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    @Override //目标资源方法运行前运行, 返回true: 放行, 放回false, 不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
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

        //获取token
        String token = req.getHeader("token");

        if (!StringUtils.hasText(token)) {
            return false;
        }

        //解析token
        String userid;
        try {
            Claims claims = JwtUtils.parseJWT(jwtProperties.getSecretKey(), token);
            userid = (String) claims.get(JwtClaimsConstant.USER_ID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("token非法");
        }

        //放行
        log.info("令牌合法, 放行");
        return true;
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
