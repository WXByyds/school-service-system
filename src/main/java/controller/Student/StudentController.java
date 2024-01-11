package controller.Student;

import Listener.ActionDoneListener;
import pojo.Student;
import service.Impl.StudentServiceImpl;
import service.StudentService;
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
import java.util.List;
import java.util.Vector;

public class StudentController extends Box {
    StudentService studentService = new StudentServiceImpl();

    final int WIDTH = 500;
    final int HEIGHT = 350;

    Box topBox = Box.createHorizontalBox();
    JPanel bottomPanel = new JPanel();

    Vector<String> title = new Vector<>();
    Vector<Vector> data = new Vector<>();

    public StudentController(JFrame f, int axis) throws Exception {
        super(axis);

        JButton insertStudent = new JButton("添加学生");
        JButton deleteStudent = new JButton("删除学生");
        JButton updateStudent = new JButton("修改学生");
        JButton exportStudent = new JButton("导出学生表");
        JButton refresh = new JButton("刷新");
        insertStudent.setBorderPainted(false);
        deleteStudent.setBorderPainted(false);
        updateStudent.setBorderPainted(false);
        exportStudent.setBorderPainted(false);
        refresh.setBorderPainted(false);

        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(insertStudent);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(deleteStudent);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(updateStudent);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(exportStudent);
        topBox.add(Box.createHorizontalStrut(20));
        topBox.add(refresh);

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
        findTf.setPreferredSize(new Dimension(80, 20));
        centerPanel.add(findTf);
        centerPanel.add(find);
        centerPanel.add(orderText);
        centerPanel.add(waySelect);
        centerPanel.add(order);
        centerPanel.setMaximumSize(new Dimension(500, 150));

        List<Student> students = studentService.selectStudent();

        title.add("学号");
        title.add("学生姓名");
        title.add("学生性别");
        title.add("学生年龄");
        title.add("学生班级");
        title.add("父母电话");

        DefaultTableModel tableModel = new DefaultTableModel(data, title);
        JTable jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        RefreshUtil.refreshStudent(data,students,tableModel);

        insertStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertDialog(f, "添加学生", new ActionDoneListener() {
                    @Override
                    public void done() {
                        List<Student> students = null;

                        try {
                            students = studentService.selectStudent();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                        RefreshUtil.refreshStudent(data,students,tableModel);
                    }
                });
            }
        });

        updateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentNo = null;
                String studentName = null;
                String studentSex = null;
                Integer studentAge = null;
                String studentClass = null;
                String studentParentPhone = null;

                int row = jTable.getSelectedRow();

                if (row >= 0) {
                    String id = (String) jTable.getValueAt(row, 0);

                    for (int i = 0; i < 6; i++) {
                        switch (i) {
                            case 0:
                                studentNo = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 1:
                                studentName = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 2:
                                studentSex = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 3:
                                studentAge = (Integer) jTable.getValueAt(row, i);
                                break;
                            case 4:
                                studentClass = String.valueOf(jTable.getValueAt(row, i));
                                break;
                            case 5:
                                studentParentPhone = String.valueOf(jTable.getValueAt(row, i));
                                break;
                        }
                    }

                    new UpdateDialog(f, "修改学生", studentNo, studentName, studentSex, studentAge, studentClass, studentParentPhone, new ActionDoneListener() {
                        @Override
                        public void done() {
                            List<Student> students = null;

                            try {
                                students = studentService.selectStudent();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }

                            RefreshUtil.refreshStudent(data,students,tableModel);
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(f, "请先选择数据！", "注意", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = jTable.getSelectedRow();

                if (row >= 0) {
                    String id = String.valueOf(jTable.getValueAt(row, 0));
                    try {
                        if (studentService.deleteStudent(id)) {
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
                List<Student> students = null;
                String studentNo = findTf.getText();

                try {
                    students = studentService.getStudentByStuNo(studentNo);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshStudent(data,students,tableModel);
            }
        });

        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> students = null;
                int ways = 0;
                if (waySelect.getSelectedIndex() == 0) {
                    ways = 1;
                }else if (waySelect.getSelectedIndex() == 1) {
                    ways = 0;
                }

                try {
                    students = studentService.orderByStuNo(ways);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshStudent(data,students,tableModel);
            }
        });

        exportStudent.addActionListener(new ActionListener() {
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

                    List<Student> students = new ArrayList<>();
                    for (Vector studentObj : data) {
                        String s1 = (String) studentObj.get(0);
                        String s2 = (String) studentObj.get(1);
                        String s3 = (String) studentObj.get(2);
                        Integer s4 = (Integer) studentObj.get(3);
                        String s5 = (String) studentObj.get(4);
                        String s6 = (String) studentObj.get(5);
                        Student student = new Student(s1,s2,s3,s4,s5,s6);
                        students.add(student);
                    }
                    ExcelUtil.ExportStudent(students, selectedFile);
                }
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> students = null;

                try {
                    students = studentService.selectStudent();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                RefreshUtil.refreshStudent(data,students,tableModel);
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
