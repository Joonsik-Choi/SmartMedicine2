package kr.co.smrp.smrp.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConMedicineAskDto {
    private String medicineName;
    private ArrayList<String> shape;
    private ArrayList<String> color;
    private ArrayList<String> formula;
    private ArrayList<String> line;
    public String getConditionNum(){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(shape.get(0).contains("전체")?"0":"1");
        stringBuffer.append(color.get(0).contains("전체")?"0":"1");
        stringBuffer.append(formula.get(0).contains("전체")?"0":"1");
        stringBuffer.append(line.get(0).contains("전체")?"0":"1");
        return stringBuffer.toString();
    }

}
