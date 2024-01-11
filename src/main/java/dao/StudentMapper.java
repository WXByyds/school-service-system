package dao;

import pojo.Course;
import pojo.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> selectStudent() throws Exception;

    boolean insertStudent(Student student) throws Exception;

    boolean updateStudent(Student student) throws Exception;

    boolean deleteStudent(String studentNo) throws Exception;

    List<Student> getStudentByStuNo(String studentNo) throws Exception;

    List<Student> orderByStuNo(int way) throws Exception;
}
