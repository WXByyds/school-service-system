package dao;

import pojo.Course;
import pojo.Teacher;

import java.util.List;

public interface TeacherMapper {
    List<Teacher> selectTeacher() throws Exception;

    boolean insertTeacher(Teacher teacher) throws Exception;

    boolean updateTeacher(Teacher teacher) throws Exception;

    boolean deleteTeacher(String teacherNo) throws Exception;

    List<Teacher> getTeacherByTNo(String teacherNo) throws Exception;

    List<Teacher> orderByTNo(int way) throws Exception;
}
