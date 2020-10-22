package kr.co.smrp.smrp.dto.medicine.info;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddMedicineInfoAskDto {
    private  String itemSeq;
    private  String itemName;
    private  String entpSeq;
    private  String entpName;
    private  String chart;
    private  String itemImage;
    private  String printFront;
    private  String printBack;
    private  String drugShape;
    private  String colorClass1;
    private  String colorClass2;
    private  String lineFront;
    private  String lineBack;
    private  String classNo;
    private  String className;
    private  String etcOtcName;
    private  String formCodeName;
    private  String formula;

    public MedicineInfo toEntity() {
        return  MedicineInfo.builder()
                .itemSeq(itemSeq)
                .itemName(itemName)
                .entpName(entpName)
                .entpSeq(entpSeq)
                .chart(chart)
                .itemImage(itemImage)
                .printFront(printFront)
                .printBack(printBack)
                .drugShape(drugShape)
                .colorClass1(colorClass1)
                .colorClass2(colorClass2)
                .lineFront(lineFront)
                .lineBack(lineBack)
                .classNo(classNo)
                .className(className)
                .etcOtcName(etcOtcName)
                .formCodeName(formCodeName)
                .formula(formula)
                .build();
        
    }
}
