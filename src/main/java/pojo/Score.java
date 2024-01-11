package pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @ExcelProperty("学号")
    private String sNo;
    @ExcelProperty("课程号")
    private String courseNo;
    @ExcelProperty("班级号")
    private String classNo;
    @ExcelProperty("课程成绩")
    private Double courseScore;
    @ExcelProperty("成绩等级")
    private String scoreGrade;
}
