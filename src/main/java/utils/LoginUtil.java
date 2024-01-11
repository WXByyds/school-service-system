package utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Set;

public class LoginUtil {
    public static boolean register(String userName, String password) throws Exception {
        Properties properties = new Properties();
        properties.setProperty(userName, password);

        properties.store(new FileWriter("D:\\java_code\\school-service-system\\src\\main\\resources\\AdminInfo.properties", true)
                , null);

        return true;
    }

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
