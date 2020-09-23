package kr.co.smrp.smrp.domain.medicine.medicineInfo;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicineInfo {
    @Id
    @GeneratedValue
    @Column(name = "MEDICINE_ID")
    private  Long Id;
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
    @OneToOne
    private MedicineEffect medicineEffect;



}