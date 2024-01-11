package utils;

import pojo.*;

import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class RefreshUtil {
    public static void refreshCourse(Vector<Vector> data, List<Course> courses, DefaultTableModel tableModel) {
        data.clear();
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Vector course = new Vector();
            Course next = iterator.next();
            course.add(next.getCourseNo());
            course.add(next.getCourseName());
            course.add(next.getClassNo());
            course.add(next.getTNo());
            course.add(next.getCourseTime());
            data.add(course);
        }
        tableModel.fireTableDataChanged();
    }

    public static void refreshTeacher(Vector<Vector> data, List<Teacher> teachers, DefaultTableModel tableModel) {
        data.clear();
        Iterator<Teacher> iterator = teachers.iterator();
        while (iterator.hasNext()) {
            Vector teacher = new Vector();
            Teacher next = iterator.next();
            teacher.add(next.getTNo());
            teacher.add(next.getTName());
            teacher.add(next.getTSex());
            teacher.add(next.getTAge());
            teacher.add(next.getTClass());
            teacher.add(next.getTSalary());
            teacher.add(next.getEntryDate());
            data.add(teacher);
        }
        tableModel.fireTableDataChanged();
    }

    public static void refreshStudent(Vector<Vector> data, List<Student> students, DefaultTableModel tableModel) {
        data.clear();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Vector student = new Vector();
            Student next = iterator.next();
            student.add(next.getSNo());
            student.add(next.getSName());
            student.add(next.getSSex());
            student.add(next.getSAge());
            student.add(next.getSClass());
            student.add(next.getSParentPhone());
            data.add(student);
        }
        tableModel.fireTableDataChanged();
    }

    public static void refreshScore(Vector<Vector> data, List<Score> scores, DefaultTableModel tableModel) {
        data.clear();
        Iterator<Score> iterator = scores.iterator();
        while (iterator.hasNext()) {
            Vector score = new Vector();
            Score next = iterator.next();
            score.add(next.getSNo());
            score.add(next.getCourseNo());
            score.add(next.getClassNo());
            score.add(next.getCourseScore());
            score.add(next.getScoreGrade());
            data.add(score);
        }
        tableModel.fireTableDataChanged();
    }

    public static void refreshAverage(Vector<Vector> data, List<Average> averages, DefaultTableModel tableModel) {
        data.clear();
        Iterator<Average> iterator = averages.iterator();
        while (iterator.hasNext()) {
            Vector average = new Vector();
            Average next = iterator.next();
            average.add(next.getClassNo());
            average.add(next.getCourseNo());
            average.add(next.getCourseAverage());
            data.add(average);
        }
        tableModel.fireTableDataChanged();
    }
}
