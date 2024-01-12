package service.Impl;

import dao.CourseMapper;
import dao.impl.CourseMapperImpl;
import pojo.Course;
import service.CourseService;

import java.util.List;

/**
 * 课程信息业务实现类
 */
public class CourseServiceImpl implements CourseService {
    /**
     * 调用课程信息数据库操作类
     * */
    CourseMapper courseMapper = new CourseMapperImpl();

    /**
     * @return 返回所有课程数据
     * @throws Exception
     */
    @Override
    public List<Course> selectCourse() throws Exception {
        return courseMapper.selectCourse();
    }

    /**
     * 给课程表插入新数据
     * @param courseNo 课程号
     * @param courseName 课程名
     * @param classNo 班级号
     * @param tNo 授课教师号
     * @param courseTime 授课时间
     * @return 是否成功插入
     * @throws Exception
     */
    @Override
    public boolean insertCourse(String courseNo, String courseName, String classNo, String tNo, String courseTime) throws Exception {
        Course course = new Course(courseNo, courseName, classNo, tNo, courseTime);

        if (courseMapper.insertCourse(course)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 修改课程数据
     * @param courseNo 课程号
     * @param courseName 课程名
     * @param classNo 班级号
     * @param tNo 授课教师号
     * @param courseTime 授课时间
     * @return 是否成功修改
     * @throws Exception
     */
    @Override
    public boolean updateCourse(String courseNo, String courseName, String classNo, String tNo, String courseTime) throws Exception {
        Course course = new Course(courseNo, courseName, classNo, tNo, courseTime);

        if (courseMapper.updateCourse(course)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 删除课程信息
     * @param courseNo
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteCourse(String courseNo) throws Exception {
        if (courseMapper.deleteCourse(courseNo)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查找课程
     * @param courseNo
     * @return
     * @throws Exception
     */
    @Override
    public List<Course> getCourseByCusNo(String courseNo) throws Exception {
        return courseMapper.getCourseByCusNo(courseNo);
    }

    /**
     * 按课程号排序
     * @param way 排序方式：0-降序 1-升序
     * @return
     * @throws Exception
     */
    @Override
    public List<Course> orderByCusNo(int way) throws Exception {
        return courseMapper.orderByCusNo(way);
    }
}
