package dao;

import pojo.Course;

import java.util.List;

/**
 * 课程信息数据库接口类
 */
public interface CourseMapper {
    List<Course> selectCourse() throws Exception;

    boolean insertCourse(Course course) throws Exception;

    boolean updateCourse(Course course) throws Exception;

    boolean deleteCourse(String courseNo) throws Exception;

    List<Course> getCourseByCusNo(String courseNo) throws Exception;

    List<Course> orderByCusNo(int way) throws Exception;
}
