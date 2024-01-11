package service.Impl;

import dao.TeacherMapper;
import dao.impl.TeacherMapperImpl;
import pojo.Teacher;
import service.TeacherService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    TeacherMapper teacherMapper = new TeacherMapperImpl();

    @Override
    public List<Teacher> selectTeacher() throws Exception {
        return teacherMapper.selectTeacher();
    }

    @Override
    public boolean insertTeacher(String tNo, String tName, String tSex, Integer tAge, String tClass, Integer tSalary,LocalDate date) throws Exception {
        LocalDate entryDate = LocalDate.now();
        if (tSalary == 0) {
            tSalary = 2000;
        }
        Teacher teacher = new Teacher(tNo,tName,tSex,tAge,tClass,tSalary,date);

        if (teacherMapper.insertTeacher(teacher)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateTeacher(String tNo, String tName, String tSex, Integer tAge, String tClass, Integer tSalary, LocalDate entryDate) throws Exception {
        Teacher teacher = new Teacher(tNo,tName,tSex,tAge,tClass,tSalary,entryDate);

        if (teacherMapper.updateTeacher(teacher)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteTeacher(String teacherNo) throws Exception {
        if (teacherMapper.deleteTeacher(teacherNo)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Teacher> getTeacherByTNo(String teacherNo) throws Exception {
        return teacherMapper.getTeacherByTNo(teacherNo);
    }

    @Override
    public List<Teacher> orderByTNo(int way) throws Exception {
        return teacherMapper.orderByTNo(way);
    }
}
