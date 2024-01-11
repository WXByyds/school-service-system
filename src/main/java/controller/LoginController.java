package controller;

import component.BackGroundPanel;
import utils.LoginUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoginController {
    JFrame f = new JFrame("登录");
    final int WIDTH = 500;
    final int HEIGHT = 350;

    BackGroundPanel bgPanel;
    Box input = Box.createVerticalBox();

    void init() throws Exception {
        Box uBox = Box.createHorizontalBox();
        JLabel username = new JLabel("用户名：");
        JTextField ut = new JTextField(15);
        ut.setBorder(null);
        ut.setToolTipText("输入用户名不能为空");

        uBox.add(username);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(ut);

        Box pBox = Box.createHorizontalBox();
        JLabel password = new JLabel("密  码： ");
        JPasswordField pt = new JPasswordField(15);
        pt.setBorder(null);
        pt.setEchoChar('*');
        pt.setToolTipText("输入密码不能为空");

        pBox.add(password);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pt);

        JButton loginButton = new JButton("登录");
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (LoginUtil.login(ut.getText(), pt.getText())) {
                        JOptionPane.showMessageDialog(f, "登录成功！即将进入系统~", "注意", JOptionPane.INFORMATION_MESSAGE);
                        f.dispose();
                        new MainController().init();
                    } else {
                        JOptionPane.showMessageDialog(f, "登录失败！", "注意", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(f, "登录失败！", "注意", JOptionPane.INFORMATION_MESSAGE);
                    exception.printStackTrace();
                }
            }
        });

        JButton registerButton = new JButton("注册");
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginUtil.register(ut.getText(), pt.getText());
                    JOptionPane.showMessageDialog(f, "注册成功！", "注意", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(f, "注册失败！", "注意", JOptionPane.INFORMATION_MESSAGE);
                    exception.printStackTrace();
                };
            }
        });

        Box bBox = Box.createHorizontalBox();
        bBox.add(loginButton);
        bBox.add(Box.createHorizontalStrut(60));
        bBox.add(registerButton);

        input.add(Box.createVerticalStrut(60));
        input.add(uBox);
        input.add(Box.createVerticalStrut(30));
        input.add(pBox);
        input.add(Box.createVerticalStrut(40));
        input.add(bBox);
        bgPanel = new BackGroundPanel(ImageIO.read(new File("src/main/resources/images/LoginBg.jpg")));
        bgPanel.add(input);

        f.setIconImage(ImageIO.read(new File("src/main/resources/images/Bg.jpg")));
        f.add(bgPanel);
        f.setBounds(500, 400, WIDTH, HEIGHT);
        f.setResizable(false);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public BackGroundPanel getPanel() {
        return this.bgPanel;
    }

    public static void show() throws Exception {
        new LoginController().init();
    }
}
