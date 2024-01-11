package dao.impl;

import dao.StudentMapper;
import pojo.Course;
import pojo.Score;
import pojo.Student;
import pojo.Teacher;
import service.StudentService;
import utils.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentMapperImpl implements StudentMapper {

    @Override
    public List<Student> selectStudent() throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from student";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Student> students = new ArrayList<>();
        while(rst.next()){
            String studentNo = rst.getString("s_no");
            String studentName = rst.getString("s_name");
            String studentSex = rst.getString("s_sex");
            Integer studentAge = rst.getInt("s_age");
            String studentClass = rst.getString("s_class");
            String sParentPhone = rst.getString("s_parent_phone");

            Student student = new Student(studentNo,studentName,studentSex,studentAge,studentClass,sParentPhone);
            students.add(student);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return students;
    }

    @Override
    public boolean insertStudent(Student student) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "insert into student values(?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, student.getSNo());
        pstmt.setString(2, student.getSName());
        pstmt.setString(3, student.getSSex());
        pstmt.setInt(4, student.getSAge());
        pstmt.setString(5, student.getSClass());
        pstmt.setString(6, student.getSParentPhone());

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
    public boolean updateStudent(Student student) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "update student set s_name = ?,s_sex = ?,s_age = ?,s_class = ?,s_parent_phone = ? where s_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(6, student.getSNo());
        pstmt.setString(1, student.getSName());
        pstmt.setString(2, student.getSSex());
        pstmt.setInt(3, student.getSAge());
        pstmt.setString(4, student.getSClass());
        pstmt.setString(5, student.getSParentPhone());

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
    public boolean deleteStudent(String studentNo) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "delete from student where s_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, studentNo);

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
    public List<Student> getStudentByStuNo(String studentNo) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from student where s_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, studentNo);

        ResultSet rst = pstmt.executeQuery();
        List<Student> students = new ArrayList<>();
        while(rst.next()){
            String stuNo = rst.getString("s_no");
            String studentName = rst.getString("s_name");
            String studentSex = rst.getString("s_sex");
            Integer studentAge = rst.getInt("s_age");
            String studentClass = rst.getString("s_class");
            String sParentPhone = rst.getString("s_parent_phone");

            Student student = new Student(stuNo,studentName,studentSex,studentAge,studentClass,sParentPhone);
            students.add(student);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return students;
    }

    @Override
    public List<Student> orderByStuNo(int way) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String ways = "";
        if (way == 0) {
            ways = "DESC";
        }else if (way == 1){
            ways = "ASC";
        }
        String sql = "select * from student order by s_no " + ways;

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Student> students = new ArrayList<>();
        while(rst.next()){
            String stuNo = rst.getString("s_no");
            String studentName = rst.getString("s_name");
            String studentSex = rst.getString("s_sex");
            Integer studentAge = rst.getInt("s_age");
            String studentClass = rst.getString("s_class");
            String sParentPhone = rst.getString("s_parent_phone");

            Student student = new Student(stuNo,studentName,studentSex,studentAge,studentClass,sParentPhone);
            students.add(student);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return students;
    }
}
