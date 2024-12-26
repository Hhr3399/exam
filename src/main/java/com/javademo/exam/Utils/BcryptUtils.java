package com.javademo.exam.Utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptUtils {
    // 使用 bcrypt 哈希密码
    public static String hashPassword(String password) {
        // 生成哈希密码，默认强度 10
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    // 验证用户输入的密码是否匹配存储的哈希
    public static boolean verifyPassword(String inputPassword, String storedHashedPassword) {
        // 使用 bcrypt 验证密码
        return BCrypt.checkpw(inputPassword, storedHashedPassword);
    }
}
