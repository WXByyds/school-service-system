package controller.Teacher;

import Listener.ActionDoneListener;
import pojo.Course;
import pojo.Teacher;
import service.Impl.TeacherServiceImpl;
import service.TeacherService;
import utils.ExcelUtil;
import utils.RefreshUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class TeacherController extends Box {
    TeacherService teacherService = new TeacherServiceImpl();

    final int WIDTH = 500;
    final int HEIGHT = 350;

    Box topBox = Box.createHorizontalBox();
    JPanel bottomPanel = new JPanel();

    Vector<String> title = new Vector<>();
    Vector<Vector> data = new Vector<>();

    public TeacherController(JFrame f, int axis) throws Exception {
        super(axis);

        JButton insertTeacher = new JButton("添加教师");
        JButton deleteTeacher = new JButton("删除教师");
        JButton updateTeacher = new JButton("修改教师");
        JButton exportTeacher = new JButton("导出教师表");
        JButton refresh = new JButton("刷新");
        insertTeacher.setBorderPainted(false);
        deleteTeacher.setBorderPainted(false);
        updateTeacher.setBorderPainted(false);
        exportTeacher.setBorderPainted(false);
        refresh.setBorderPainted(false);

        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(insertTeacher);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(deleteTeacher);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(updateTeacher);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(exportTeacher);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(refresh);

        JLabel findText = new JLabel("请输入教师号：");
        JTextField findTf = new JTextField();
        JButton find = new JButton("查找");
        find.setBorderPainted(false);

        String[] ways = {"升序", "降序"};
        JComboBox waySelect = new JComboBox(ways);
        JLabel orderText = new JLabel("按教师号排");
        JButton order = new JButton("确定");
        order.setBorderPainted(false);

        JPanel centerPanel = new JPanel();
        centerPanel.add(findText);
        findTf.setPreferredSize(new Dimension(80, 20));
        centerPanel.add(findTf);
        centerPanel.add(find);
        centerPanel.add(orderText);
        centerPanel.add(waySelect);
        centerPanel.add(order);
        centerPanel.setMaximumSize(new Dimension(500, 150));

        List<Teacher> teachers = teacherService.selectTeacher();

        title.add("教师号");
        title.add("教师姓名");
        title.add("性别");
        title.add("教师年龄");
        title.add("管理班级");
        title.add("薪资");
        title.add("入职时间");

        DefaultTableModel tableModel = new DefaultTableModel(data, title);
        JTable jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        RefreshUtil.refreshTeacher(data,teachers,tableModel);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        insertTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertDialog(f, "添加教师", new ActionDoneListener() {
                    @Override
                    public void done() {
                        List<Teacher> teachers = null;

                        try {
                            teachers = teacherService.selectTeacher();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                        RefreshUtil.refreshTeacher(data,teachers,tableModel);
                    }
                });
            }
        });

        updateTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tNo = null;
                String tName = null;
                String tSex = null;
                Integer tAge = null;
                String tClass = null;
                Integer tSalary = null;
                LocalDate entryDate = null;

                int row = jTable.getSelectedRow();

                if (row >= 0) {
                    String id = (String) jTable.getValueAt(row, 0);

                    for (int i = 0; i < 7; i++) {
                        switch (i) {
                            case 0:
                                tNo = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 1:
                                tName = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 2:
                                tSex = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 3:
                                tAge = (Integer) jTable.getValueAt(row, i);
                                break;
                            case 4:
                                tClass = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 5:
                                tSalary = (Integer) jTable.getValueAt(row, i);
                                break;
                            case 6:
                                entryDate = LocalDate.parse(jTable.getValueAt(row, i).toString(), dateTimeFormatter);
                        }
                    }

                    new UpdateDialog(f, "修改教师", tNo, tName, tSex, tAge, tClass, tSalary, entryDate, dateTimeFormatter, new ActionDoneListener() {
                        @Override
                        public void done() {
                            List<Teacher> teachers = null;

                            try {
                                teachers = teacherService.selectTeacher();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }

                            RefreshUtil.refreshTeacher(data,teachers,tableModel);
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(f, "请先选择数据！", "注意", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = jTable.getSelectedRow();
                if (row >= 0) {
                    String id = String.valueOf(jTable.getValueAt(row, 0));
                    try {
                        if (teacherService.deleteTeacher(id)) {
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
                List<Teacher> teachers = null;
                String teacherNo = findTf.getText();

                try {
                    teachers = teacherService.getTeacherByTNo(teacherNo);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshTeacher(data,teachers,tableModel);
            }
        });

        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Teacher> teachers = null;
                int ways = 0;
                if (waySelect.getSelectedIndex() == 0) {
                    ways = 1;
                }else if (waySelect.getSelectedIndex() == 1) {
                    ways = 0;
                }

                try {
                    teachers = teacherService.orderByTNo(ways);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshTeacher(data,teachers,tableModel);
            }
        });

        exportTeacher.addActionListener(new ActionListener() {
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

                    List<Teacher> teachers = new ArrayList<>();
                    for (Vector teacherObj : data) {
                        String t1 = (String) teacherObj.get(0);
                        String t2 = (String) teacherObj.get(1);
                        String t3 = (String) teacherObj.get(2);
                        Integer t4 = (Integer) teacherObj.get(3);
                        String t5 = (String) teacherObj.get(4);
                        Integer t6 = (Integer) teacherObj.get(5);
                        LocalDate t7 = (LocalDate) teacherObj.get(6);
                        Teacher teacher = new Teacher(t1,t2,t3,t4,t5,t6,t7);
                        teachers.add(teacher);
                    }
                    ExcelUtil.ExportTeacher(teachers, selectedFile);
                }
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Teacher> teachers = null;

                try {
                    teachers = teacherService.selectTeacher();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshTeacher(data,teachers,tableModel);
            }
        });

        JScrollPane bottomPane = new JScrollPane(jTable);

        bottomPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        bottomPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        bottomPanel.add(bottomPane);
        topBox.setMaximumSize(new Dimension(WIDTH, 60));

        this.add(Box.createVerticalStrut(10));
        this.add(topBox);
        this.add(Box.createVerticalStrut(5));
        this.add(centerPanel);
        this.add(bottomPane);
    }
}
