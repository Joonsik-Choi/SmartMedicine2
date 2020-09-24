package kr.co.smrp.smrp.domain.medicine.medicineInfo;
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicineEffect {
    @Id
    @GeneratedValue
    @Column(name = "MEDICINE_EFFECT_ID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "MEDICINE_ID")
    private MedicineInfo medicineInfo;
    @Lob
    private String effect;
    @Lob
    private String usageCapacity;
    @Lob
    private String precautions;

   
}