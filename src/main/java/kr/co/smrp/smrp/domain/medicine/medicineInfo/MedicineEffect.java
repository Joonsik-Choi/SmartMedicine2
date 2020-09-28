package kr.co.smrp.smrp.domain.medicine.medicineInfo;
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.smrp.smrp.dto.medicine.MedicineEffectAskDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class MedicineEffect {
    @Id
    @GeneratedValue
    @Column(name = "MEDICINE_EFFECT_ID")
    private Long id;
    @Setter
    @OneToOne
    @JoinColumn(name = "MEDICINE_ID")
    private MedicineInfo medicineInfo;
    @Lob
    private String effect;
    @Lob
    private String usageCapacity;
    @Lob
    private String precautions;
    public MedicineEffect(MedicineEffectAskDto medicineEffectAskDto){
        effect=medicineEffectAskDto.getEffect();
        usageCapacity=medicineEffectAskDto.getUsageCapacity();
        precautions=medicineEffectAskDto.getPrecautions();
    }
}