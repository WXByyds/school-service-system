package service;

import pojo.Course;

import java.util.List;

public interface CourseService {
    List<Course> selectCourse() throws Exception;

    boolean insertCourse(String courseNo, String courseName, String classNo, String tNo, String courseTime) throws Exception;

    boolean updateCourse(String courseNo, String courseName, String classNo, String tNo, String courseTime) throws Exception;

    boolean deleteCourse(String courseNo) throws Exception;

    List<Course> getCourseByCusNo(String courseNo) throws Exception;

    List<Course> orderByCusNo(int way) throws Exception;
}
