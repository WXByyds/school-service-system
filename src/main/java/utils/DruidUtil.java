package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidUtil {
    private static DataSource ds;

    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }

    public static void init() throws Exception {
        Properties pro = new Properties();
        pro.load(new FileInputStream("D:\\java_code\\school-service-system\\src\\main\\resources\\druid.properties"));
        ds = DruidDataSourceFactory.createDataSource(pro);
    }

}
