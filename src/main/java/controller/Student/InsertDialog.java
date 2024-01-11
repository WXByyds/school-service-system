package controller.Student;

import Listener.ActionDoneListener;
import service.Impl.StudentServiceImpl;
import service.StudentService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertDialog extends JDialog{
    StudentService studentService = new StudentServiceImpl();

    ActionDoneListener listener;

    JTextField sNoTf = new JTextField();
    JTextField sNameTf = new JTextField();
    Box sSexChoiceBox = Box.createHorizontalBox();
    ButtonGroup sSexChoice = new ButtonGroup();
    JRadioButton male = new JRadioButton("男", true);
    JRadioButton female = new JRadioButton("女");
    JTextField sAgeTf = new JTextField();
    JTextField sClassTf = new JTextField();
    JTextField sParentPhoneTf = new JTextField();

    JLabel sNo = new JLabel("学号：");
    JLabel sName = new JLabel("学生姓名：");
    JLabel sSex = new JLabel("学生性别：");
    JLabel sAge = new JLabel("学生年龄：");
    JLabel sClass = new JLabel("学生班级：");
    JLabel sParentPhone = new JLabel("父母电话：");

    JButton ok = new JButton("确认");
    JButton esc = new JButton("取消");

    Box allBox = Box.createVerticalBox();
    Box finalBox = Box.createHorizontalBox();

    public InsertDialog(JFrame f, String title, ActionDoneListener listener) {
        super(f, title);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentNo = sNoTf.getText();
                String studentName = sNameTf.getText();
                String studentSex = "男";
                if (male.isSelected()) {
                    studentSex = "男";
                }else if (female.isSelected()){
                    studentSex = "女";
                }
                Integer studentAge = Integer.valueOf(sAgeTf.getText());
                String studentClass = sClassTf.getText();
                String studentParentPhone = sParentPhoneTf.getText();

                try {
                    if (studentService.insertStudent(studentNo,studentName,studentSex,studentAge,studentClass,studentParentPhone)) {
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

        sSexChoice.add(male);
        sSexChoice.add(female);
        sSexChoiceBox.add(male);
        sSexChoiceBox.add(female);

        Box sNoBox = Box.createHorizontalBox();
        sNoBox.add(sNo);
        sNoBox.add(Box.createHorizontalStrut(10));
        sNoBox.add(sNoTf);

        Box sNameBox = Box.createHorizontalBox();
        sNameBox.add(sName);
        sNameBox.add(Box.createHorizontalStrut(10));
        sNameBox.add(sNameTf);

        Box sSexBox = Box.createHorizontalBox();
        sSexBox.add(sSex);
        sSexBox.add(Box.createHorizontalStrut(10));
        sSexBox.add(male);
        sSexBox.add(female);
        sSexBox.add(Box.createHorizontalStrut(310));

        Box sAgeBox = Box.createHorizontalBox();
        sAgeBox.add(sAge);
        sAgeBox.add(Box.createHorizontalStrut(10));
        sAgeBox.add(sAgeTf);

        Box sClassBox = Box.createHorizontalBox();
        sClassBox.add(sClass);
        sClassBox.add(Box.createHorizontalStrut(10));
        sClassBox.add(sClassTf);

        Box sParentPhoneBox = Box.createHorizontalBox();
        sParentPhoneBox.add(sParentPhone);
        sParentPhoneBox.add(Box.createHorizontalStrut(10));
        sParentPhoneBox.add(sParentPhoneTf);

        Box bBox = Box.createHorizontalBox();
        bBox.add(ok);
        bBox.add(Box.createHorizontalStrut(20));
        bBox.add(esc);

        allBox.add(Box.createVerticalStrut(30));
        allBox.add(sNoBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(sNameBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(sSexBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(sAgeBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(sClassBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(sParentPhoneBox);
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
