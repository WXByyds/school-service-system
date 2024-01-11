package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Average {
    private String classNo;
    private String courseNo;
    private Double courseAverage;
}
