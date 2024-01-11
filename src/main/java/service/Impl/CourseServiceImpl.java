package service.Impl;

import dao.CourseMapper;
import dao.impl.CourseMapperImpl;
import pojo.Course;
import service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    CourseMapper courseMapper = new CourseMapperImpl();

    @Override
    public List<Course> selectCourse() throws Exception {
        return courseMapper.selectCourse();
    }

    @Override
    public boolean insertCourse(String courseNo, String courseName, String classNo, String tNo, String courseTime) throws Exception {
        Course course = new Course(courseNo, courseName, classNo, tNo, courseTime);

        if (courseMapper.insertCourse(course)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateCourse(String courseNo, String courseName, String classNo, String tNo, String courseTime) throws Exception {
        Course course = new Course(courseNo, courseName, classNo, tNo, courseTime);

        if (courseMapper.updateCourse(course)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteCourse(String courseNo) throws Exception {
        if (courseMapper.deleteCourse(courseNo)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Course> getCourseByCusNo(String courseNo) throws Exception {
        return courseMapper.getCourseByCusNo(courseNo);
    }

    @Override
    public List<Course> orderByCusNo(int way) throws Exception {
        return courseMapper.orderByCusNo(way);
    }
}
