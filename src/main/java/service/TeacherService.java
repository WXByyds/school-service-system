package service;

import pojo.Course;
import pojo.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {
    List<Teacher> selectTeacher() throws Exception;

    boolean insertTeacher(String tNo, String tName, String tSex, Integer tAge, String tClass, Integer tSalary, LocalDate date) throws Exception;

    boolean updateTeacher(String tNo, String tName, String tSex, Integer tAge, String tClass, Integer tSalary, LocalDate entryDate) throws Exception;

    boolean deleteTeacher(String teacherNo) throws Exception;

    List<Teacher> getTeacherByTNo(String teacherNo) throws Exception;

    List<Teacher> orderByTNo(int way) throws Exception;
}
