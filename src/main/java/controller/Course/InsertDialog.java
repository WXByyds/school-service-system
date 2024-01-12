package controller.Course;

import Listener.ActionDoneListener;
import service.CourseService;
import service.Impl.CourseServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * 添加课程对话框
 */
public class InsertDialog extends JDialog {
    CourseService courseService = new CourseServiceImpl();

    ActionDoneListener listener;

    JTextField cNoTf = new JTextField();
    JTextField cNameTf = new JTextField();
    JTextField classNoTf = new JTextField();
    JTextField tNoTf = new JTextField();

    String[] cTimeString = {"星期一", "星期二", "星期三", "星期四", "星期五", "待定"};
    JComboBox<String> cTimeSelect = new JComboBox<>(cTimeString);

    JLabel cNo = new JLabel("课程号：");
    JLabel cName = new JLabel("课程名：");
    JLabel classNo = new JLabel("班级号：");
    JLabel tNo = new JLabel("授课教师号：");
    JLabel cTime = new JLabel("授课日期：");

    JButton ok = new JButton("确认");
    JButton esc = new JButton("取消");

    Box allBox = Box.createVerticalBox();
    Box finalBox = Box.createHorizontalBox();

    /**
     * 生成课程添加对话框
     * @param f 父窗口
     * @param title 对话框标题
     * @param listener 数据刷新的监听
     */
    public InsertDialog(JFrame f, String title, ActionDoneListener listener) {
        super(f, title);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseNo = cNoTf.getText();
                String courseName = cNameTf.getText();
                String classNo = classNoTf.getText();
                String tNo = tNoTf.getText();
                String courseTime = (String) cTimeSelect.getSelectedItem();

                try {
                    if (courseService.insertCourse(courseNo, courseName, classNo, tNo, courseTime)) {
                        JOptionPane.showMessageDialog(f, "添加成功！", "注意", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        listener.done();
                    }else {
                        JOptionPane.showMessageDialog(f, "添加失败！请重新检查输入信息！", "注意", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(f, "添加失败！请重新检查输入信息！", "注意", JOptionPane.INFORMATION_MESSAGE);
                    exception.printStackTrace();
                }

            }
        });

        esc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        Box cNoBox = Box.createHorizontalBox();
        cNoBox.add(cNo);
        cNoBox.add(Box.createHorizontalStrut(10));
        cNoBox.add(cNoTf);

        Box cNameBox = Box.createHorizontalBox();
        cNameBox.add(cName);
        cNameBox.add(Box.createHorizontalStrut(10));
        cNameBox.add(cNameTf);

        Box classNoBox = Box.createHorizontalBox();
        classNoBox.add(classNo);
        classNoBox.add(Box.createHorizontalStrut(10));
        classNoBox.add(classNoTf);

        Box tNoBox = Box.createHorizontalBox();
        tNoBox.add(tNo);
        tNoBox.add(Box.createHorizontalStrut(10));
        tNoBox.add(tNoTf);

        Box cTimeBox = Box.createHorizontalBox();
        cTimeBox.add(cTime);
        cTimeBox.add(Box.createHorizontalStrut(10));
        cTimeBox.add(cTimeSelect);

        Box bBox = Box.createHorizontalBox();
        bBox.add(ok);
        bBox.add(Box.createHorizontalStrut(20));
        bBox.add(esc);

        allBox.add(Box.createVerticalStrut(30));
        allBox.add(cNoBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(cNameBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(classNoBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(tNoBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(cTimeBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(bBox);
        allBox.add(Box.createVerticalStrut(20));

        finalBox.add(Box.createHorizontalStrut(15));
        finalBox.add(allBox);
        finalBox.add(Box.createHorizontalStrut(15));

        this.add(finalBox);
        this.setBounds(500, 350, 500, 350);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
