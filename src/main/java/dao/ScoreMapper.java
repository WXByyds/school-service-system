package dao;

import pojo.Average;
import pojo.Course;
import pojo.Score;
import pojo.Student;

import java.util.List;

public interface ScoreMapper {
    List<Score> selectScore() throws Exception;

    boolean insertScore(Score score) throws Exception;

    boolean updateScore(Score score) throws Exception;

    boolean deleteScore(String studentNo, String courseNo) throws Exception;

    Double avgScore(String classNo, String courseNo, Double courseScore) throws Exception;

    boolean updateAverage(String classNo, String courseNo, Double courseScore) throws Exception;

    List<Score> getScoreByStuNo(String studentNo) throws Exception;

    List<Score> orderByStuNo(int way) throws Exception;

    boolean insertAverage(String classNo, String courseNo, Double courseAverage) throws Exception;

    List<Average> selectAverageAll() throws Exception;

    List<Average> selectAverageOne(String classNos, String courseNos) throws Exception;
}
