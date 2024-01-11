package controller.Teacher;

import Listener.ActionDoneListener;
import service.CourseService;
import service.Impl.CourseServiceImpl;
import service.Impl.TeacherServiceImpl;
import service.TeacherService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class InsertDialog extends JDialog {
    TeacherService teacherService = new TeacherServiceImpl();

    ActionDoneListener listener;

    JTextField tNoTf = new JTextField();
    JTextField tNameTf = new JTextField();
    Box tSexChoiceBox = Box.createHorizontalBox();
    ButtonGroup tSexChoice = new ButtonGroup();
    JRadioButton male = new JRadioButton("男", true);
    JRadioButton female = new JRadioButton("女");    JTextField tAgeTf = new JTextField();
    JTextField tClassTf = new JTextField();
    JTextField tSalaryTf = new JTextField();

    JLabel tNo = new JLabel("教师号：");
    JLabel tName = new JLabel("教师名：");
    JLabel tSex = new JLabel("教师性别：");
    JLabel tAge = new JLabel("教师年龄：");
    JLabel tClass = new JLabel("管理班级：");
    JLabel tSalary = new JLabel("薪资：");

    JButton ok = new JButton("确认");
    JButton esc = new JButton("取消");

    Box allBox = Box.createVerticalBox();
    Box finalBox = Box.createHorizontalBox();

    public InsertDialog(JFrame f, String title, ActionDoneListener listener) {
        super(f, title);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teacherNo = tNoTf.getText();
                String teacherName = tNameTf.getText();
                String teacherSex = "男";
                if (male.isSelected()) {
                    teacherSex = "男";
                }else if (female.isSelected()){
                    teacherSex = "女";
                }
                String teacherAge = tAgeTf.getText();
                String teacherClass = tClassTf.getText();
                String teacherSalary = tSalaryTf.getText();
                if (teacherSalary == null || teacherSalary.equals("")) {
                    teacherSalary = "0";
                }

                try {
                    if (teacherService.insertTeacher(teacherNo, teacherName, teacherSex, Integer.valueOf((teacherAge)), teacherClass, Integer.parseInt(teacherSalary),LocalDate.now())) {
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

        tSexChoice.add(male);
        tSexChoice.add(female);
        tSexChoiceBox.add(male);
        tSexChoiceBox.add(female);

        Box tNoBox = Box.createHorizontalBox();
        tNoBox.add(tNo);
        tNoBox.add(Box.createHorizontalStrut(10));
        tNoBox.add(tNoTf);

        Box tNameBox = Box.createHorizontalBox();
        tNameBox.add(tName);
        tNameBox.add(Box.createHorizontalStrut(10));
        tNameBox.add(tNameTf);

        Box tSexBox = Box.createHorizontalBox();
        tSexBox.add(tSex);
        tSexBox.add(Box.createHorizontalStrut(10));
        tSexBox.add(male);
        tSexBox.add(female);
        tSexBox.add(Box.createHorizontalStrut(310));

        Box tAgeBox = Box.createHorizontalBox();
        tAgeBox.add(tAge);
        tAgeBox.add(Box.createHorizontalStrut(10));
        tAgeBox.add(tAgeTf);

        Box tClassBox = Box.createHorizontalBox();
        tClassBox.add(tClass);
        tClassBox.add(Box.createHorizontalStrut(10));
        tClassBox.add(tClassTf);

        Box tSalaryBox = Box.createHorizontalBox();
        tSalaryBox.add(tSalary);
        tSalaryBox.add(Box.createHorizontalStrut(10));
        tSalaryBox.add(tSalaryTf);

        Box bBox = Box.createHorizontalBox();
        bBox.add(ok);
        bBox.add(Box.createHorizontalStrut(20));
        bBox.add(esc);

        allBox.add(Box.createVerticalStrut(15));
        allBox.add(tNoBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(tNameBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(tSexBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(tAgeBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(tClassBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(tSalaryBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(bBox);
        allBox.add(Box.createVerticalStrut(20));

        finalBox.add(Box.createHorizontalStrut(15));
        finalBox.add(allBox);
        finalBox.add(Box.createHorizontalStrut(15));

        this.add(finalBox);
        this.setBounds(500, 350, 500, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
