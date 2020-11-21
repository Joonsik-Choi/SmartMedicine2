package kr.co.smrp.smrp.domain.medicine.medicineInfo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Transactional
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class MedicineInfo {
    @Id
    @GeneratedValue
    @Column(name = "MEDICINE_ID")
    private  Long id;
    private  String itemSeq;
    private  String itemName;
    private  String entpSeq;
    private  String entpName;
    @Column(length = 1000)
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
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private MedicineEffect medicineEffect;
}