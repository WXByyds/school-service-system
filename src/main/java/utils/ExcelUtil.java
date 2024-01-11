package utils;

import com.alibaba.excel.EasyExcel;
import pojo.Course;
import pojo.Score;
import pojo.Student;
import pojo.Teacher;

import java.io.File;
import java.util.List;

public class ExcelUtil {
    public static void ExportCourse(List<Course> courses, File file) {
        EasyExcel.write(file, Course.class).sheet("sheet1").doWrite(courses);
    }

    public static void ExportTeacher(List<Teacher> teachers, File file) {
        EasyExcel.write(file, Teacher.class).sheet("sheet1").doWrite(teachers);
    }

    public static void ExportStudent(List<Student> students, File file) {
        EasyExcel.write(file, Student.class).sheet("sheet1").doWrite(students);
    }

    public static void ExportScore(List<Score> scores, File file) {
        EasyExcel.write(file, Score.class).sheet("sheet1").doWrite(scores);
    }
}