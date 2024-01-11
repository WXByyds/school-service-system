package dao.impl;

import dao.ScoreMapper;
import pojo.Average;
import pojo.Course;
import pojo.Score;
import pojo.Student;
import service.ScoreService;
import utils.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoreMapperImpl implements ScoreMapper {
    @Override
    public List<Score> selectScore() throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from score";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Score> scores = new ArrayList<>();
        while(rst.next()){
            String studentNo = rst.getString("s_no");
            String courseNo = rst.getString("course_no");
            String classNo = rst.getString("class_no");
            Double courseScore = rst.getDouble("course_score");
            String scoreGrade = rst.getString("score_grade");

            Score score = new Score(studentNo,courseNo,classNo,courseScore,scoreGrade);
            scores.add(score);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return scores;
    }

    @Override
    public boolean insertScore(Score score) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "insert into score values(?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, score.getSNo());
        pstmt.setString(2, score.getCourseNo());
        pstmt.setString(3, score.getClassNo());
        pstmt.setDouble(4, score.getCourseScore());
        pstmt.setString(5, score.getScoreGrade());

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
    public boolean updateScore(Score score) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "update score set class_no = ?,course_score = ? where s_no = ? and course_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, score.getClassNo());
        pstmt.setDouble(2, score.getCourseScore());
        pstmt.setString(3, score.getSNo());
        pstmt.setString(4, score.getCourseNo());

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
    public boolean deleteScore(String studentNo, String courseNo) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "delete from score where s_no = ? and course_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, studentNo);
        pstmt.setString(2, courseNo);

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
    public Double avgScore(String classNo, String courseNo, Double courseScore) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select avg(course_score) 'avgScore' from score where class_no = ? and course_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, classNo);
        pstmt.setString(2, courseNo);

        Double avg = courseScore;
        ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("avgScore") != null){
                    avg = Double.valueOf(resultSet.getString("avgScore"));
                }
            }

        resultSet.close();
        pstmt.close();
        conn.close();

        return avg;
    }

    @Override
    public boolean updateAverage(String classNo, String courseNo, Double courseAverage) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "update average set course_average = ? where course_no = ? and class_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(3, classNo);
        pstmt.setString(2, courseNo);
        pstmt.setDouble(1, courseAverage);

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
    public List<Score> getScoreByStuNo(String studentNo) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from score where s_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, studentNo);

        ResultSet rst = pstmt.executeQuery();
        List<Score> scores = new ArrayList<>();
        while(rst.next()){
            String stuNo = rst.getString("s_no");
            String courseNo = rst.getString("course_no");
            String classNo = rst.getString("class_no");
            Double courseScore = rst.getDouble("course_score");
            String scoreGrade = rst.getString("score_grade");

            Score score = new Score(stuNo,courseNo,classNo,courseScore,scoreGrade);
            scores.add(score);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return scores;
    }

    @Override
    public List<Score> orderByStuNo(int way) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String ways = "";
        if (way == 0) {
            ways = "DESC";
        }else if (way == 1){
            ways = "ASC";
        }
        String sql = "select * from score order by s_no " + ways;

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Score> scores = new ArrayList<>();
        while(rst.next()){
            String stuNo = rst.getString("s_no");
            String courseNo = rst.getString("course_no");
            String classNo = rst.getString("class_no");
            Double courseScore = rst.getDouble("course_score");
            String scoreGrade = rst.getString("score_grade");

            Score score = new Score(stuNo,courseNo,classNo,courseScore,scoreGrade);
            scores.add(score);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return scores;
    }

    @Override
    public boolean insertAverage(String classNo, String courseNo, Double courseAverage) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "insert into average values(?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, classNo);
        pstmt.setString(2, courseNo);
        pstmt.setDouble(3, courseAverage);

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
    public List<Average> selectAverageAll() throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from average";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rst = pstmt.executeQuery();
        List<Average> averages = new ArrayList<>();
        while(rst.next()){
            String courseNo = rst.getString("course_no");
            String classNo = rst.getString("class_no");
            Double courseScore = rst.getDouble("course_average");

            Average average = new Average(classNo,courseNo,courseScore);
            averages.add(average);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return averages;
    }

    @Override
    public List<Average> selectAverageOne(String classNos, String courseNos) throws Exception {
        Connection conn = DruidUtil.getConnection();

        String sql = "select * from average where class_no = ? and course_no = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,classNos);
        pstmt.setString(2,courseNos);

        ResultSet rst = pstmt.executeQuery();
        List<Average> averages = new ArrayList<>();
        while(rst.next()){
            String courseNo = rst.getString("course_no");
            String classNo = rst.getString("class_no");
            Double courseScore = rst.getDouble("course_average");

            Average average = new Average(classNo,courseNo,courseScore);
            averages.add(average);
        }

        rst.close();
        pstmt.close();
        conn.close();
        return averages;
    }

}
