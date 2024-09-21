package ltwst2.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Phương thức để mã hóa mật khẩu
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Phương thức để kiểm tra mật khẩu
    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }}


