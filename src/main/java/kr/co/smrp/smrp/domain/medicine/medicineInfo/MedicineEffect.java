package kr.co.smrp.smrp.domain.medicine.medicineInfo;
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.smrp.smrp.dto.medicine.effect.MedicineEffectAskDto;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Transactional
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class MedicineEffect {
    @Id
    @GeneratedValue
    @Column(name = "MEDICINE_EFFECT_ID")
    private Long id;
    //@Setter
   //@OneToOne(fetch = FetchType.LAZY)
   //@JoinColumn(name = "MEDICINE_ID")
    //private MedicineInfo medicineInfo;
    @Lob
    private String effect;
    @Lob
    private String usageCapacity;
    @Lob
    private String precautions;

    public String getEffect() {
        return Optional.of(effect).orElse("");
    }

    public String getUsageCapacity() {
        return  Optional.of(usageCapacity).orElse("");
    }

    public String getPrecautions() {
        return  Optional.of(precautions).orElse("");
    }

    public MedicineEffect(MedicineEffectAskDto medicineEffectAskDto){
        Optional<String> effect= Optional.of(medicineEffectAskDto.getEffect());
        Optional<String> usageCapacity= Optional.of(medicineEffectAskDto.getEffect());
        Optional<String> precautions= Optional.of(medicineEffectAskDto.getEffect());
        this.effect=effect.orElse("");
        this.usageCapacity=usageCapacity.orElse("");
        this.precautions=precautions.orElse("");
    }
}