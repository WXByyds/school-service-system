package controller.Score;

import Listener.ActionDoneListener;
import pojo.Course;
import pojo.Score;
import pojo.Student;
import service.Impl.ScoreServiceImpl;
import service.ScoreService;
import utils.ExcelUtil;
import utils.RefreshUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ScoreController extends Box {
    ScoreService scoreService = new ScoreServiceImpl();

    final int WIDTH = 500;
    final int HEIGHT = 350;

    Box topBox = Box.createHorizontalBox();

    Vector<String> title = new Vector<>();
    Vector<Vector> data = new Vector<>();

    public ScoreController(JFrame f,int axis) throws Exception {
        super(axis);

        JButton insertScore = new JButton("添加成绩");
        JButton deleteScore = new JButton("删除成绩");
        JButton updateScore = new JButton("修改成绩");
        JButton exportScore = new JButton("导出成绩表");
        JButton refresh = new JButton("刷新");
        JButton average = new JButton("查看平均成绩");
        insertScore.setBorderPainted(false);
        deleteScore.setBorderPainted(false);
        updateScore.setBorderPainted(false);
        exportScore.setBorderPainted(false);
        refresh.setBorderPainted(false);
        average.setBorderPainted(false);

        topBox.add(Box.createHorizontalStrut(10));
        topBox.add(insertScore);
        topBox.add(Box.createHorizontalStrut(10));
        topBox.add(deleteScore);
        topBox.add(Box.createHorizontalStrut(10));
        topBox.add(updateScore);
        topBox.add(Box.createHorizontalStrut(10));
        topBox.add(exportScore);
        topBox.add(Box.createHorizontalStrut(10));
        topBox.add(refresh);
        topBox.add(Box.createHorizontalStrut(10));
        topBox.add(average);

        JLabel findText = new JLabel("请输入学生号：");
        JTextField findTf = new JTextField();
        JButton find = new JButton("查找");
        find.setBorderPainted(false);

        String[] ways = {"升序", "降序"};
        JComboBox waySelect = new JComboBox(ways);
        JLabel orderText = new JLabel("按学号排");
        JButton order = new JButton("确定");
        order.setBorderPainted(false);

        JPanel centerPanel = new JPanel();
        centerPanel.add(findText);
        findTf.setPreferredSize(new Dimension(120, 20));
        centerPanel.add(findTf);
        centerPanel.add(find);
        centerPanel.add(orderText);
        centerPanel.add(waySelect);
        centerPanel.add(order);
        centerPanel.setMaximumSize(new Dimension(500, 150));

        List<Score> scores = scoreService.selectScore();

        title.add("学号");
        title.add("课程号");
        title.add("班级号");
        title.add("成绩");
        title.add("成绩等级");

        DefaultTableModel tableModel = new DefaultTableModel(data, title);
        JTable jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        RefreshUtil.refreshScore(data,scores,tableModel);

        insertScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertDialog(f, "添加成绩", new ActionDoneListener() {
                    @Override
                    public void done() {
                        List<Score> scores = null;

                        try {
                            scores = scoreService.selectScore();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                        RefreshUtil.refreshScore(data,scores,tableModel);
                    }
                });
            }
        });

        updateScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentNo = null;
                String courseNo = null;
                String classNo = null;
                Double courseScore = null;

                int row = jTable.getSelectedRow();

                if (row >= 0) {
                    String id = (String) jTable.getValueAt(row, 0);

                    for (int i = 0; i < 6; i++) {
                        switch (i) {
                            case 0:
                                studentNo = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 1:
                                courseNo = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 2:
                                classNo = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 3:
                                courseScore = (double) jTable.getValueAt(row, i);
                                break;
                        }
                    }

                    new UpdateDialog(f, "修改成绩", studentNo, courseNo, classNo, courseScore, new ActionDoneListener() {
                        @Override
                        public void done() {
                            List<Score> scores = null;

                            try {
                                scores = scoreService.selectScore();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }

                            RefreshUtil.refreshScore(data,scores,tableModel);

                            tableModel.fireTableDataChanged();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(f, "请先选择数据！", "注意", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = jTable.getSelectedRow();

                if (row >= 0) {
                    String id1 = String.valueOf(jTable.getValueAt(row, 0));
                    String id2 = String.valueOf(jTable.getValueAt(row, 1));
                    String id3 = String.valueOf(jTable.getValueAt(row, 2));
                    try {
                        if (scoreService.deleteScore(id1, id2, id3)) {
                            JOptionPane.showMessageDialog(f, "删除成功！", "注意", JOptionPane.INFORMATION_MESSAGE);
                            tableModel.removeRow(row);
                            tableModel.fireTableDataChanged();
                        } else {
                            JOptionPane.showMessageDialog(f, "删除失败！", "注意", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(f, "删除失败！", "注意", JOptionPane.INFORMATION_MESSAGE);
                        exception.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "请先选择数据！", "注意", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Score> scores = null;
                String studentNo = findTf.getText();

                try {
                    scores = scoreService.getScoreByStuNo(studentNo);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshScore(data,scores,tableModel);
            }
        });

        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Score> scores = null;
                int ways = 0;
                if (waySelect.getSelectedIndex() == 0) {
                    ways = 1;
                }else if (waySelect.getSelectedIndex() == 1) {
                    ways = 0;
                }

                try {
                    scores = scoreService.orderByStuNo(ways);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshScore(data,scores,tableModel);
            }
        });

        exportScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser exportChooser = new JFileChooser("D:\\java_code\\school-service-system\\src\\main\\resources\\excelfiles");
                exportChooser.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        String filename = f.getName().toLowerCase();
                        if (filename.endsWith(".xlsx") || filename.endsWith(".xls")) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "Excel文件(*.xls,*.xlsx)";
                    }
                });

                int result = exportChooser.showSaveDialog(f);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = exportChooser.getSelectedFile();

                    List<Score> scores = new ArrayList<>();
                    for (Vector scoreObj : data) {
                        String s1 = (String) scoreObj.get(0);
                        String s2 = (String) scoreObj.get(1);
                        String s3 = (String) scoreObj.get(2);
                        Double s4 = (Double) scoreObj.get(3);
                        String s5 = (String) scoreObj.get(4);
                        Score score = new Score(s1,s2,s3,s4,s5);
                        scores.add(score);
                    }
                    ExcelUtil.ExportScore(scores, selectedFile);
                }
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Score> scores = null;

                try {
                    scores = scoreService.selectScore();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshScore(data,scores,tableModel);
            }
        });

        average.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AverageDialog(f, "课程平均分");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JScrollPane bottomPane = new JScrollPane(jTable);

        bottomPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        topBox.setMaximumSize(new Dimension(580, 60));

        this.add(Box.createVerticalStrut(10));
        this.add(topBox);
        this.add(Box.createVerticalStrut(5));
        this.add(centerPanel);
        this.add(bottomPane);
    }
}
