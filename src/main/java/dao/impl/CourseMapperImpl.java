package dao.impl;

import dao.CourseMapper;
import pojo.Course;
import utils.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseMapperImpl implements CourseMapper {
    @Override
    public List<Course> selectCourse() throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from course";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Course> courses = new ArrayList<>();
        while(rst.next()){
            String courseNo = rst.getString("course_no");
            String courseName = rst.getString("course_name");
            String classNo = rst.getString("class_no");
            String tNo = rst.getString("t_no");
            String courseTime = rst.getString("course_time");

            Course course = new Course(courseNo,courseName,classNo,tNo,courseTime);
            courses.add(course);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return courses;
    }

    @Override
    public boolean insertCourse(Course course) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "insert into course values(?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, course.getCourseNo());
        pstmt.setString(2, course.getCourseName());
        pstmt.setString(3, course.getClassNo());
        pstmt.setString(4, course.getTNo());
        pstmt.setString(5, course.getCourseTime());

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
    public boolean updateCourse(Course course) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "update course set course_name = ?,t_no = ?,course_time = ? where course_no = ? and class_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(4, course.getCourseNo());
        pstmt.setString(1, course.getCourseName());
        pstmt.setString(5, course.getClassNo());
        pstmt.setString(2, course.getTNo());
        pstmt.setString(3, course.getCourseTime());

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
    public boolean deleteCourse(String courseNo) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "delete from course where course_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, courseNo);

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
    public List<Course> getCourseByCusNo(String courseNo) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from course where course_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, courseNo);

        ResultSet rst = pstmt.executeQuery();
        List<Course> courses = new ArrayList<>();
        while(rst.next()){
            String cNo = rst.getString("course_no");
            String courseName = rst.getString("course_name");
            String classNo = rst.getString("class_no");
            String tNo = rst.getString("t_no");
            String courseTime = rst.getString("course_time");

            Course course = new Course(cNo,courseName,classNo,tNo,courseTime);
            courses.add(course);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return courses;
    }

    @Override
    public List<Course> orderByCusNo(int way) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String ways = "";
        if (way == 0) {
            ways = "DESC";
        }else if (way == 1){
            ways = "ASC";
        }
        String sql = "select * from course order by course_no " + ways;

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Course> courses = new ArrayList<>();
        while(rst.next()){
            String cNo = rst.getString("course_no");
            String courseName = rst.getString("course_name");
            String classNo = rst.getString("class_no");
            String tNo = rst.getString("t_no");
            String courseTime = rst.getString("course_time");

            Course course = new Course(cNo,courseName,classNo,tNo,courseTime);
            courses.add(course);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return courses;
    }
}
