package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程平均分实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Average {
    private String classNo;
    private String courseNo;
    private Double courseAverage;
}
