package pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @ExcelProperty("教师号")
    private String tNo;
    @ExcelProperty("教师姓名")
    private String tName;
    @ExcelProperty("教师性别")
    private String tSex;
    @ExcelProperty("教师年龄")
    private Integer tAge;
    @ExcelProperty("管理班级")
    private String tClass;
    @ExcelProperty("教师薪资")
    private Integer tSalary;
    @ColumnWidth(10)
    @ExcelProperty("入职时间")
    private LocalDate entryDate;
}
