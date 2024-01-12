package service.Impl;

import dao.StudentMapper;
import dao.impl.StudentMapperImpl;
import pojo.Student;
import service.StudentService;

import java.util.List;

/**
 * 学生信息业务实现类
 */
public class StudentServiceImpl implements StudentService {
    StudentMapper studentMapper = new StudentMapperImpl();

    @Override
    public List<Student> selectStudent() throws Exception {
        return studentMapper.selectStudent();
    }

    @Override
    public boolean insertStudent(String studentNo, String sName, String sSex, Integer sAge, String sClass, String studentParentPhone) throws Exception {
        Student student = new Student(studentNo,sName,sSex,sAge,sClass,studentParentPhone);

        if (studentMapper.insertStudent(student)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateStudent(String studentNo, String sName, String sSex, Integer sAge, String sClass, String studentParentPhone) throws Exception {
        Student student = new Student(studentNo,sName,sSex,sAge,sClass,studentParentPhone);

        if (studentMapper.updateStudent(student)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteStudent(String studentNo) throws Exception {
        if (studentMapper.deleteStudent(studentNo)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Student> getStudentByStuNo(String studentNo) throws Exception {
        return studentMapper.getStudentByStuNo(studentNo);
    }

    @Override
    public List<Student> orderByStuNo(int way) throws Exception {
        return studentMapper.orderByStuNo(way);
    }
}
