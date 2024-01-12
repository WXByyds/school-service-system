package utils;

import com.alibaba.excel.EasyExcel;
import pojo.Course;
import pojo.Score;
import pojo.Student;
import pojo.Teacher;

import java.io.File;
import java.util.List;

/**
 * Excel文件导出工具类
 */
public class ExcelUtil {
    /**
     * @param courses 要导出的List集合数据
     * @param file 要导出到哪个文件
     */
    public static void ExportCourse(List<Course> courses, File file) {
        EasyExcel.write(file, Course.class).sheet("sheet1").doWrite(courses);
    }

    /**
     * @param teachers 要导出的List集合数据
     * @param file 要导出到哪个文件
     */
    public static void ExportTeacher(List<Teacher> teachers, File file) {
        EasyExcel.write(file, Teacher.class).sheet("sheet1").doWrite(teachers);
    }

    /**
     * @param students 要导出的List集合数据
     * @param file 要导出到哪个文件
     */
    public static void ExportStudent(List<Student> students, File file) {
        EasyExcel.write(file, Student.class).sheet("sheet1").doWrite(students);
    }

    /**
     * @param scores 要导出的List集合数据
     * @param file 要导出到哪个文件
     */
    public static void ExportScore(List<Score> scores, File file) {
        EasyExcel.write(file, Score.class).sheet("sheet1").doWrite(scores);
    }
}