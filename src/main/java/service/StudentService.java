package service;

import pojo.Course;
import pojo.Student;

import java.util.List;

/**
 * 学生信息业务接口类
 */
public interface StudentService {
    List<Student> selectStudent() throws Exception;

    boolean insertStudent(String studentNo, String sName, String sSex, Integer sAge, String sClass, String studentParentPhone) throws Exception;

    boolean updateStudent(String studentNo, String sName, String sSex, Integer sAge, String sClass, String studentParentPhone) throws Exception;

    boolean deleteStudent(String studentNo) throws Exception;

    List<Student> getStudentByStuNo(String studentNo) throws Exception;

    List<Student> orderByStuNo(int way) throws Exception;
}
