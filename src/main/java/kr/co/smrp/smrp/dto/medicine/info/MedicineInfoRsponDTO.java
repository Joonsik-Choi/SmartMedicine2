package kr.co.smrp.smrp.dto.medicine.info;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineEffect;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import lombok.*;

import org.springframework.transaction.annotation.Transactional;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Transactional
public class MedicineInfoRsponDTO {
    private  Long id;
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
    private String effect;
    private String usageCapacity;
    private String precautions;

    public MedicineInfoRsponDTO(MedicineInfo medicineInfo){
        id = medicineInfo.getId();
        this.itemSeq = medicineInfo.getItemSeq();
        this.itemName = medicineInfo.getItemName();
        this.entpSeq = medicineInfo.getEntpSeq();
        this.entpName = medicineInfo.getEntpName();
        this.chart = medicineInfo.getChart();
        this.itemImage = medicineInfo.getItemImage();
        this.printFront = medicineInfo.getPrintFront();
        this.printBack = medicineInfo.getPrintBack();
        this.drugShape = medicineInfo.getDrugShape();
        this.colorClass1 = medicineInfo.getColorClass1();
        this.colorClass2 = medicineInfo.getColorClass2();
        this.lineFront = medicineInfo.getLineFront();
        this.lineBack = medicineInfo.getLineBack();
        this.classNo = medicineInfo.getClassNo();
        this.className = medicineInfo.getClassName();
        this.etcOtcName = medicineInfo.getEtcOtcName();
        this.formCodeName = medicineInfo.getFormCodeName();
        this.formula = medicineInfo.getFormula();
        if(medicineInfo.getMedicineEffect()!=null) {
            this.effect = medicineInfo.getMedicineEffect().getEffect();
            this.usageCapacity = medicineInfo.getMedicineEffect().getUsageCapacity();
            this.precautions=medicineInfo.getMedicineEffect().getPrecautions();
        }
        else{
            effect="";
            usageCapacity="";
            precautions="";
        }
    }
}
