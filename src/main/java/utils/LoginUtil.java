package utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Set;

/**
 * 管理员登录工具类
 */
public class LoginUtil {
    /**
     * 将用户名和密码写入管理员信息配置文件
     * @param userName 用户名
     * @param password 用户密码
     * @return 注册是否成功
     * @throws Exception
     */
    public static boolean register(String userName, String password) throws Exception {
        Properties properties = new Properties();
        properties.setProperty(userName, password);

        properties.store(new FileWriter("D:\\java_code\\school-service-system\\src\\main\\resources\\AdminInfo.properties", true)
                , null);

        return true;
    }

    /**
     * 读取管理员信息的配置文件，检查是否存在该管理员信息
     * @param userName 用户名
     * @param password 密码
     * @return 登录是否成功
     * @throws Exception
     */
    public static boolean login(String userName, String password) throws Exception {
        Properties properties = new Properties();
        boolean flag = false;

        properties.load(new FileReader("D:\\java_code\\school-service-system\\src\\main\\resources\\AdminInfo.properties"));
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            String value = properties.getProperty(key);
            if (userName.equals(key) && password.equals(value)) {
                flag = true;
            }
        }
        return flag;
    }
}
