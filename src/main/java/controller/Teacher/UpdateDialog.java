package controller.Teacher;

import Listener.ActionDoneListener;
import service.Impl.TeacherServiceImpl;
import service.TeacherService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UpdateDialog extends JDialog {
    TeacherService teacherService = new TeacherServiceImpl();

    ActionDoneListener listener;

    JTextField tNoTf = new JTextField();
    JTextField tNameTf = new JTextField();
    ButtonGroup tSexChoice = new ButtonGroup();
    JRadioButton male = new JRadioButton("男", true);
    JRadioButton female = new JRadioButton("女");
    JTextField tAgeTf = new JTextField();
    JTextField tClassTf = new JTextField();
    JTextField tSalaryTf = new JTextField();
    JTextField entryDateTf = new JTextField();

    JLabel tNo = new JLabel("教师号：");
    JLabel tName = new JLabel("教师名：");
    JLabel tSex = new JLabel("教师性别：");
    JLabel tAge = new JLabel("教师年龄：");
    JLabel tClass = new JLabel("管理班级：");
    JLabel tSalary = new JLabel("薪资：");
    JLabel entryDateLabel = new JLabel("入职日期：");

    JButton ok = new JButton("确认");
    JButton esc = new JButton("取消");

    Box allBox = Box.createVerticalBox();
    Box finalBox = Box.createHorizontalBox();

    public UpdateDialog(JFrame f, String title, String teacherNos, String teacherNames, String tSexs, Integer tAges, String tClasss, Integer tSalarys, LocalDate entryDate, DateTimeFormatter dateTimeFormatter, ActionDoneListener listener) {
        super(f, title);

        tSexChoice.add(male);
        tSexChoice.add(female);

        if (teacherNos != null) {
            tNoTf.setText(teacherNos);
        }
        if (teacherNames != null) {
            tNameTf.setText(teacherNames);
        }
        if (tSexs != null) {
            if (tSexs.equals("男")){
                male.setSelected(true);
            }else if (tSexs.equals("女")){
                female.setSelected(true);
            }        }
        if (tAges != null) {
            tAgeTf.setText(String.valueOf(tAges));
        }
        if (tClasss != null) {
            tClassTf.setText(tClasss);
        }
        if (tSalarys != null) {
            tSalaryTf.setText(String.valueOf(tSalarys));
        }
        if (entryDate != null) {
            entryDateTf.setText(entryDate.toString());
        }


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
                String entryDate = entryDateTf.getText();
                LocalDate date = LocalDate.parse(entryDate, dateTimeFormatter);

                try {
                    if (teacherService.updateTeacher(teacherNo, teacherName, teacherSex, Integer.valueOf(teacherAge), teacherClass, Integer.valueOf(teacherSalary), date)) {
                        JOptionPane.showMessageDialog(f, "更新成功！", "注意", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        listener.done();
                    }else {
                        JOptionPane.showMessageDialog(f, "更新失败！请重新检查输入信息！", "注意", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(f, "更新失败！请重新检查输入信息！", "注意", JOptionPane.INFORMATION_MESSAGE);
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

        Box entryDateBox = Box.createHorizontalBox();
        entryDateBox.add(entryDateLabel);
        entryDateBox.add(Box.createHorizontalStrut(10));
        entryDateBox.add(entryDateTf);

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
        allBox.add(Box.createVerticalStrut(30));
        allBox.add(tClassBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(tSalaryBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(entryDateBox);
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
