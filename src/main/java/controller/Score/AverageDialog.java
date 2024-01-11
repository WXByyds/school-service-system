package controller.Score;

import pojo.Average;
import service.Impl.ScoreServiceImpl;
import service.ScoreService;
import utils.RefreshUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class AverageDialog extends JDialog {
    ScoreService scoreService = new ScoreServiceImpl();

    Vector<String> dataTitle = new Vector<>();
    Vector<Vector> data = new Vector<>();

    public AverageDialog(JFrame f, String title) throws Exception {
        super(f,title);

        dataTitle.add("班级号");
        dataTitle.add("课程号");
        dataTitle.add("课程平均分");

        List<Average> averages = scoreService.selectAverageAll();
        DefaultTableModel tableModel = new DefaultTableModel(data, dataTitle);
        JTable jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        RefreshUtil.refreshAverage(data, averages,tableModel);

        JScrollPane bottomPane = new JScrollPane(jTable);
        bottomPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(bottomPane);
        this.setBounds(500, 350, 300, 200);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
