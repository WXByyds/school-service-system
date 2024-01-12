import dao.CourseMapper;
import dao.ScoreMapper;
import dao.impl.CourseMapperImpl;
import dao.impl.ScoreMapperImpl;
import org.junit.Test;
import pojo.Course;
import service.CourseService;
import service.Impl.CourseServiceImpl;
import service.Impl.ScoreServiceImpl;
import service.Impl.StudentServiceImpl;
import service.Impl.TeacherServiceImpl;
import service.ScoreService;
import service.StudentService;
import service.TeacherService;
import utils.DruidUtil;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class test {

    @Test
    public void register() {
        Properties properties = new Properties();
        properties.setProperty("zhangsan", "123");
        try {
            properties.store(new FileWriter("D:\\java_code\\school-service-system\\src\\main\\resources\\AdminInfo.properties", true)
            , null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void login() {
        Properties properties = new Properties();
        boolean flag = false;
        try {
            properties.load(new FileReader("D:\\java_code\\school-service-system\\src\\main\\resources\\AdminInfo.properties"));
            Set<String> keys = properties.stringPropertyNames();
            for (String key : keys) {
                String value = properties.getProperty(key);
                if(key.equals("wangwu") && value.equals("123")) {
                    flag = true;
                }
            }
            System.out.println(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertCourseData() {
        try {
            DruidUtil.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CourseService courseService = new CourseServiceImpl();
        String[] courseNames = {"语文","数学","英语","体育","美术","物理","生物","地理","政治","化学","心理健康"};
        String[] courseNos = {"101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111"};
        String[] courseTimes = {"星期一", "星期二", "星期三", "星期四", "星期五"};
        String courseNo = null;
        String courseName = null;
        String classNo = null;
        String tNo = null;
        String courseTime = null;
        Random random = new Random(100000);
        for (int i = 0; i < 310; i++) {
             courseNo = courseNos[random.nextInt(11)];
             courseName = courseNames[(Integer.parseInt(courseNo))%100-1];
             classNo = String.valueOf(random.nextInt(9000)+1000);
             tNo = String.valueOf(random.nextInt(9000)+1000);
             courseTime = courseTimes[random.nextInt(5)];
            try {
                courseService.insertCourse(courseNo,courseName,classNo,tNo,courseTime);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }



    @Test
    public void insertStudentData() throws ParseException {
        try {
            DruidUtil.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        StudentService studentService = new StudentServiceImpl();
        String[] name1= {"王", "张", "许", "彭", "田", "周", "陈", "李", "姜", "杨", "邓", "刘","黄","郭","唐","石","吕","毛"};
        String[] name2 = {"伟","芳", "秀英","文迪","静","洋","杰","艳","涛","强","磊","军","刚","敏","超","丽","娜","雅琪","娟","子乔"};
        String[] sex = {"男", "女"};

        String sNo;
        String sName;
        String sSex;
        int sAge;
        String sClass;
        String sParentPhone;
        Random random = new Random(100000);
        Random random1 = new Random(1000000000);
        for (int i = 0; i < 310; i++) {
            sNo = String.valueOf(random.nextInt(9000) + 1000);
            sName = name1[random.nextInt(18)] + name2[random.nextInt(20)];
            sSex = sex[random.nextInt(2)];
            sAge = random.nextInt(9) + 8;
            sClass = String.valueOf(random.nextInt(9000) + 1000);
            String before = String.valueOf(random1.nextInt(70) + 130);
            String center = String.valueOf(random1.nextInt(900) + 1000);
            String after = String.valueOf(random1.nextInt(900) + 1000);
            sParentPhone = before + center + after;
            try {
                studentService.insertStudent(sNo, sName, sSex, sAge, sClass, sParentPhone);
            } catch (Exception e) {

            }
        }
    }

    @Test
    public void testSelectCourse() throws Exception {
        DruidUtil.init();
        CourseService courseService = new CourseServiceImpl();
        List<Course> courses =  courseService.selectCourse();
        System.out.println(courses.size());
    }
}
