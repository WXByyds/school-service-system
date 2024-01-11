package pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @ExcelProperty("课程号")
    private String courseNo;
    @ExcelProperty("课程名")
    private String courseName;
    @ExcelProperty("班级号")
    private String classNo;
    @ExcelProperty("教师")
    private String tNo;
    @ExcelProperty("授课时间")
    private String courseTime;
}
