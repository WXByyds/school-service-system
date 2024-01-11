package dao.impl;

import dao.TeacherMapper;
import pojo.Course;
import pojo.Student;
import pojo.Teacher;
import utils.DruidUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherMapperImpl implements TeacherMapper {
    @Override
    public List<Teacher> selectTeacher() throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from teacher";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Teacher> teachers = new ArrayList<>();
        while(rst.next()){
            String tNo = rst.getString("t_no");
            String tName = rst.getString("t_name");
            String tSex = rst.getString("t_sex");
            Integer tAge = rst.getInt("t_age");
            String tClass = rst.getString("t_class");
            Integer tSalary = rst.getInt("t_salary");
            LocalDate entryDate =  LocalDate.parse(rst.getDate("entry_date").toString());

            Teacher teacher = new Teacher(tNo, tName, tSex, tAge, tClass, tSalary, entryDate);
            teachers.add(teacher);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return teachers;
    }

    @Override
    public boolean insertTeacher(Teacher teacher) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "insert into teacher values(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, teacher.getTNo());
        pstmt.setString(2, teacher.getTName());
        pstmt.setString(3, teacher.getTSex());
        pstmt.setInt(4, teacher.getTAge());
        pstmt.setString(5, teacher.getTClass());
        pstmt.setInt(6, teacher.getTSalary());
        pstmt.setDate(7, Date.valueOf(teacher.getEntryDate()));

        int count = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        if (count > 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateTeacher(Teacher teacher) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "update teacher set t_name = ?,t_sex = ?,t_Age = ?,t_class = ?,t_salary = ?,entry_date = ? where t_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(7, teacher.getTNo());
        pstmt.setString(1, teacher.getTName());
        pstmt.setString(2, teacher.getTSex());
        pstmt.setInt(3, teacher.getTAge());
        pstmt.setString(4, teacher.getTClass());
        pstmt.setInt(5, teacher.getTSalary());
        pstmt.setDate(6, Date.valueOf(teacher.getEntryDate()));

        int count = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        if (count > 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteTeacher(String teacherNo) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "delete from teacher where t_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, teacherNo);

        int count = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        if (count > 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Teacher> getTeacherByTNo(String teacherNo) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from teacher where t_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, teacherNo);

        ResultSet rst = pstmt.executeQuery();
        List<Teacher> teachers = new ArrayList<>();
        while(rst.next()){
            String tNo = rst.getString("t_no");
            String tName = rst.getString("t_name");
            String tSex = rst.getString("t_sex");
            Integer tAge = rst.getInt("t_age");
            String tClass = rst.getString("t_class");
            Integer tSalary = rst.getInt("t_salary");
            LocalDate entryDate =  LocalDate.parse(rst.getDate("entry_date").toString());

            Teacher teacher = new Teacher(tNo, tName, tSex, tAge, tClass, tSalary, entryDate);
            teachers.add(teacher);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return teachers;
    }

    @Override
    public List<Teacher> orderByTNo(int way) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String ways = "";
        if (way == 0) {
            ways = "DESC";
        }else if (way == 1){
            ways = "ASC";
        }
        String sql = "select * from teacher order by t_no " + ways;

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Teacher> teachers = new ArrayList<>();
        while(rst.next()){
            String tNo = rst.getString("t_no");
            String tName = rst.getString("t_name");
            String tSex = rst.getString("t_sex");
            Integer tAge = rst.getInt("t_age");
            String tClass = rst.getString("t_class");
            Integer tSalary = rst.getInt("t_salary");
            LocalDate entryDate =  LocalDate.parse(rst.getDate("entry_date").toString());

            Teacher teacher = new Teacher(tNo, tName, tSex, tAge, tClass, tSalary, entryDate);
            teachers.add(teacher);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return teachers;
    }
}
