package service;

import pojo.Average;
import pojo.Course;
import pojo.Score;

import java.util.List;

/**
 * 成绩信息业务接口类
 */
public interface ScoreService {
    List<Score> selectScore() throws Exception;

    boolean insertScore(String studentNo, String courseNo, String classNo, Double courseScore) throws Exception;

    boolean updateScore(String studentNo, String courseNo, String classNo, Double courseScore) throws Exception;

    boolean deleteScore(String studentNo, String courseNo, String classNo) throws Exception;

    List<Score> getScoreByStuNo(String studentNo) throws Exception;

    List<Score> orderByStuNo(int way) throws Exception;

    List<Average> selectAverageAll() throws Exception;
}
