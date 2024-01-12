package controller;

import component.BackGroundPanel;
import controller.Course.CourseController;
import controller.Score.ScoreController;
import controller.Student.StudentController;
import controller.Teacher.TeacherController;
import lombok.SneakyThrows;
import service.StudentService;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;

/**
 * 系统主界面，可根据需要选择要处理的模块
 * 模块分为：课程管理、教师管理、学生管理、学生成绩管理
 */
public class MainController {
    JFrame f = new JFrame("中小学家校服务系统");
    final int WIDTH = 800;
    final int HEIGHT = 650;

    JSplitPane jsp = new JSplitPane();

    /**
     * 初始化界面
     */
    void init() throws Exception {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
        DefaultMutableTreeNode courseManage = new DefaultMutableTreeNode("课程管理");
        DefaultMutableTreeNode teacherManage = new DefaultMutableTreeNode("教师管理");
        DefaultMutableTreeNode studentManage = new DefaultMutableTreeNode("学生管理");
        DefaultMutableTreeNode scoreManage = new DefaultMutableTreeNode("学生成绩管理");

        root.add(courseManage);
        root.add(teacherManage);
        root.add(studentManage);
        root.add(scoreManage);

        JPanel bgPanel = new JPanel();
        JLabel welcome = new JLabel("欢迎来到中小学家校服务系统~");
        Box welcomeBox = Box.createVerticalBox();

        welcomeBox.add(Box.createVerticalStrut(280));
        welcomeBox.add(welcome);
        welcomeBox.add(Box.createVerticalStrut(100));
        bgPanel.add(welcomeBox);
        jsp.setRightComponent(bgPanel);
        JTree jTree = new JTree(root);


        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object lastPathComponent = jTree.getSelectionPath().getLastPathComponent();

                if (courseManage.equals(lastPathComponent)) {
                    CourseController courseController = null;
                    try {
                        courseController = new CourseController(f, BoxLayout.Y_AXIS);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    courseController.setBounds(85, 0, WIDTH - 85, HEIGHT);
                    jsp.setRightComponent(courseController);
                }else if (teacherManage.equals(lastPathComponent)) {
                    TeacherController teacherController = null;
                    try {
                        teacherController = new TeacherController(f, BoxLayout.Y_AXIS);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    teacherController.setBounds(85, 0, WIDTH - 85, HEIGHT);
                    jsp.setRightComponent(teacherController);
                }else if (studentManage.equals(lastPathComponent)) {
                    StudentController studentController = null;
                    try {
                        studentController = new StudentController(f, BoxLayout.Y_AXIS);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    studentController.setBounds(85, 0, WIDTH - 85, HEIGHT);
                    jsp.setRightComponent(studentController);
                }else if (scoreManage.equals(lastPathComponent)) {
                    ScoreController scoreController = null;
                    try {
                        scoreController = new ScoreController(f, BoxLayout.Y_AXIS);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    scoreController.setBounds(85, 0, WIDTH - 85, HEIGHT);
                    jsp.setRightComponent(scoreController);
                }else if (root.equals(lastPathComponent)) {
                    jsp.setRightComponent(bgPanel);
                }
            }
        });

        jsp.setLeftComponent(jTree);
        jTree.setBounds(0, 0, 80, 350);

        f.setIconImage(ImageIO.read(new File("src/main/resources/images/Bg.jpg")));
        f.add(jsp);
        f.setBounds(500, 400, WIDTH, HEIGHT);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
