package service.Impl;

import dao.ScoreMapper;
import dao.impl.ScoreMapperImpl;
import pojo.Average;
import pojo.Score;
import pojo.Student;
import service.ScoreService;

import java.util.List;

/**
 * 成绩信息业务实现类
 */
public class ScoreServiceImpl implements ScoreService {
    ScoreMapper scoreMapper = new ScoreMapperImpl();

    @Override
    public List<Score> selectScore() throws Exception {
        return scoreMapper.selectScore();
    }

    @Override
    public boolean insertScore(String studentNo, String courseNo, String classNo, Double courseScore) throws Exception {
        String scoreGrade;
        if (courseScore >= 80) {
            scoreGrade = "优";
        }else if (courseScore >= 60) {
            scoreGrade = "良";
        }else {
            scoreGrade = "差";
        }

        Score score = new Score(studentNo,courseNo,classNo,courseScore,scoreGrade);
        boolean flag = scoreMapper.insertScore(score);

        if (flag) {
            Double courseAverage = scoreMapper.avgScore(classNo, courseNo, courseScore);
            if (scoreMapper.selectAverageOne(classNo, courseNo).isEmpty()) {
                scoreMapper.insertAverage(classNo, courseNo, courseAverage);
            }else {
                scoreMapper.updateAverage(classNo, courseNo, courseAverage);
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateScore(String studentNo, String courseNo, String classNo, Double courseScore) throws Exception {
        String scoreGrade;
        if (courseScore >= 80) {
            scoreGrade = "优";
        }else if (courseScore >= 60) {
            scoreGrade = "良";
        }else {
            scoreGrade = "差";
        }

        Score score = new Score(studentNo,courseNo,classNo,courseScore,scoreGrade);
        boolean flag = scoreMapper.updateScore(score);

        if (flag) {
            Double courseAverage = scoreMapper.avgScore(classNo, courseNo, courseScore);
            if (scoreMapper.selectAverageOne(classNo, courseNo).isEmpty()) {
                scoreMapper.insertAverage(classNo, courseNo, courseAverage);
            }else {
                scoreMapper.updateAverage(classNo, courseNo, courseAverage);
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteScore(String studentNo, String courseNo, String classNo) throws Exception {
        if (scoreMapper.deleteScore(studentNo, courseNo)) {
            Double courseAverage = scoreMapper.avgScore(classNo, courseNo, 0.0);
            scoreMapper.updateAverage(classNo, courseNo, courseAverage);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Score> getScoreByStuNo(String studentNo) throws Exception {
        return scoreMapper.getScoreByStuNo(studentNo);
    }

    @Override
    public List<Score> orderByStuNo(int way) throws Exception {
        return scoreMapper.orderByStuNo(way);
    }

    @Override
    public List<Average> selectAverageAll() throws Exception {
        return scoreMapper.selectAverageAll();
    }

}
