package pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @ExcelProperty("学号")
    private String sNo;
    @ExcelProperty("学生姓名")
    private String sName;
    @ExcelProperty("学生性别")
    private String sSex;
    @ExcelProperty("学生年龄")
    private Integer sAge;
    @ExcelProperty("班级号")
    private String sClass;
    @ExcelProperty("家长电话")
    private String sParentPhone;
}
