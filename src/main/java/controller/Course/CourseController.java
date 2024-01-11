package controller.Course;

import Listener.ActionDoneListener;
import pojo.Course;
import service.CourseService;
import service.Impl.CourseServiceImpl;
import utils.ExcelUtil;
import utils.RefreshUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/*
* 课程信息管理主界面
* */
public class CourseController extends Box {
    CourseService courseService = new CourseServiceImpl();

    final int WIDTH = 500;
    final int HEIGHT = 350;

    Box topBox = Box.createHorizontalBox();

    Vector<String> title = new Vector<>();
    Vector<Vector> data = new Vector<>();

    public CourseController(JFrame f, int axis) throws Exception {
        super(axis);

        JButton insertCourse = new JButton("添加课程");
        JButton deleteCourse = new JButton("删除课程");
        JButton updateCourse = new JButton("修改课程");
        JButton exportCourse = new JButton("导出课程表");
        JButton refresh = new JButton("刷新");
        insertCourse.setBorderPainted(false);
        deleteCourse.setBorderPainted(false);
        updateCourse.setBorderPainted(false);
        exportCourse.setBorderPainted(false);
        refresh.setBorderPainted(false);

        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(insertCourse);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(deleteCourse);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(updateCourse);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(exportCourse);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(refresh);

        JLabel findText = new JLabel("请输入课程号：");
        JTextField findTf = new JTextField();
        JButton find = new JButton("查找");
        find.setBorderPainted(false);

        String[] ways = {"升序", "降序"};
        JComboBox waySelect = new JComboBox(ways);
        JLabel orderText = new JLabel("按课程号排");
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

        List<Course> courses = courseService.selectCourse();

        title.add("课程号");
        title.add("课程名");
        title.add("班级号");
        title.add("授课教师号");
        title.add("授课时间");

        DefaultTableModel tableModel = new DefaultTableModel(data, title);
        JTable jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        RefreshUtil.refreshCourse(data,courses,tableModel);

        insertCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertDialog(f, "添加课程", new ActionDoneListener() {
                    @Override
                    public void done() {
                        List<Course> courses = null;

                        try {
                            courses = courseService.selectCourse();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                        RefreshUtil.refreshCourse(data,courses,tableModel);
                    }
                });
            }
        });

        updateCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseNo = null;
                String courseName = null;
                String classNo = null;
                String tNo = null;
                String courseTime = null;

                int row = jTable.getSelectedRow();

                if (row >= 0) {
                    String id = (String) jTable.getValueAt(row, 0);

                    for (int i = 0; i < 5; i++) {
                        switch (i) {
                            case 0:
                                courseNo = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 1:
                                courseName = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 2:
                                classNo = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 3:
                                tNo = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 4:
                                courseTime = String.valueOf(jTable.getValueAt(row, i));
                                break;
                        }
                    }

                    new UpdateDialog(f, "修改课程", courseNo, courseName, classNo, tNo, courseTime, new ActionDoneListener() {
                        @Override
                        public void done() {
                            List<Course> courses = null;

                            try {
                                courses = courseService.selectCourse();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }

                            RefreshUtil.refreshCourse(data,courses,tableModel);
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(f, "请先选择数据！", "注意", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = jTable.getSelectedRow();
                if (row >= 0) {
                    String id = String.valueOf(jTable.getValueAt(row, 0));
                    try {
                        if (courseService.deleteCourse(id)) {
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
                }else {
                    JOptionPane.showMessageDialog(f, "请先选择数据！", "注意", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Course> courses = null;
                String courseNo = findTf.getText();

                try {
                    courses = courseService.getCourseByCusNo(courseNo);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshCourse(data,courses,tableModel);
            }
        });

        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Course> courses = null;
                int ways = 0;
                if (waySelect.getSelectedIndex() == 0) {
                    ways = 1;
                }else if (waySelect.getSelectedIndex() == 1) {
                    ways = 0;
                }

                try {
                    courses = courseService.orderByCusNo(ways);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshCourse(data,courses,tableModel);
            }
        });

        exportCourse.addActionListener(new ActionListener() {
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

                    List<Course> courses = new ArrayList<>();
                    for (Vector courseObj : data) {
                        String c1 = (String) courseObj.get(0);
                        String c2 = (String) courseObj.get(1);
                        String c3 = (String) courseObj.get(2);
                        String c4 = (String) courseObj.get(3);
                        String c5 = (String) courseObj.get(4);
                        Course course = new Course(c1,c2,c3,c4,c5);
                        courses.add(course);
                    }
                    ExcelUtil.ExportCourse(courses, selectedFile);
                }
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Course> courses = null;

                try {
                    courses = courseService.selectCourse();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshCourse(data,courses,tableModel);
            }
        });

        JScrollPane bottomPane = new JScrollPane(jTable);

        bottomPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        topBox.setMaximumSize(new Dimension(WIDTH, 60));

        this.add(Box.createVerticalStrut(10));
        this.add(topBox);
        this.add(Box.createVerticalStrut(5));
        this.add(centerPanel);
        this.add(bottomPane);
    }
}
