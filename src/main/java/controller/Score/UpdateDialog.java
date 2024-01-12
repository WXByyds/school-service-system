package controller.Score;

import Listener.ActionDoneListener;
import service.Impl.ScoreServiceImpl;
import service.ScoreService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 成绩修改对话框
 */
public class UpdateDialog extends JDialog {
    ScoreService scoreService = new ScoreServiceImpl();

    ActionDoneListener listener;

    JTextField sNoTf = new JTextField();
    JTextField cNoTf = new JTextField();
    JTextField classNoTf = new JTextField();
    JTextField courseScoreTf = new JTextField();

    JLabel sNo = new JLabel("学号：");
    JLabel cNo = new JLabel("课程号：");
    JLabel classNo = new JLabel("班级号：");
    JLabel courseScore = new JLabel("成绩：");

    JButton ok = new JButton("确认");
    JButton esc = new JButton("取消");

    Box allBox = Box.createVerticalBox();
    Box finalBox = Box.createHorizontalBox();

    /**
     * 生成一个成绩修改对话框
     * @param f 父窗口
     * @param title 标题
     * @param studentNos 学号
     * @param courseNos 课程号
     * @param classNos 班级号
     * @param courseScores 课程成绩
     * @param listener 数据刷新的监听
     */
    public UpdateDialog(JFrame f, String title, String studentNos, String courseNos, String classNos, Double courseScores, ActionDoneListener listener) {
        super(f, title);

        if (studentNos != null) {
            sNoTf.setText(studentNos);
        }
        if (courseNos != null) {
            cNoTf.setText(courseNos);
        }
        if (classNos != null) {
            classNoTf.setText(classNos);
        }
        if (courseScores != null) {
            courseScoreTf.setText(String.valueOf(courseScores));
        }

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentNo = sNoTf.getText();
                String courseNo = cNoTf.getText();
                String classNo = classNoTf.getText();
                Double courseScore = Double.valueOf(courseScoreTf.getText());

                try {
                    if (scoreService.updateScore(studentNo,courseNo,classNo,courseScore)) {
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

        Box sNoBox = Box.createHorizontalBox();
        sNoBox.add(sNo);
        sNoBox.add(Box.createHorizontalStrut(10));
        sNoBox.add(sNoTf);

        Box cNoBox = Box.createHorizontalBox();
        cNoBox.add(cNo);
        cNoBox.add(Box.createHorizontalStrut(10));
        cNoBox.add(cNoTf);

        Box classNoBox = Box.createHorizontalBox();
        classNoBox.add(classNo);
        classNoBox.add(Box.createHorizontalStrut(10));
        classNoBox.add(classNoTf);

        Box courseScoreBox = Box.createHorizontalBox();
        courseScoreBox.add(courseScore);
        courseScoreBox.add(Box.createHorizontalStrut(10));
        courseScoreBox.add(courseScoreTf);

        Box bBox = Box.createHorizontalBox();
        bBox.add(ok);
        bBox.add(Box.createHorizontalStrut(20));
        bBox.add(esc);

        allBox.add(Box.createVerticalStrut(20));
        allBox.add(sNoBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(cNoBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(classNoBox);
        allBox.add(Box.createVerticalStrut(20));
        allBox.add(courseScoreBox);
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
