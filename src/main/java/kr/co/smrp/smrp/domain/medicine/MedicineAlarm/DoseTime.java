package kr.co.smrp.smrp.domain.medicine.MedicineAlarm;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class DoseTime {
    @Enumerated(EnumType.STRING)
    private YesOrNo morning;
    @Enumerated(EnumType.STRING)
    private YesOrNo lunch;
    @Enumerated(EnumType.STRING)
    private YesOrNo dinner;
    public int totalDose(){
        int total=0;
        if(morning.equals(YesOrNo.Y))total++;
        if(lunch.equals(YesOrNo.Y))total++;
        if(dinner.equals(YesOrNo.Y))total++;
        return total;
    }
    public boolean isMorning(){
        return morning.equals(YesOrNo.Y);
    }
    public boolean isLunch(){
        return lunch.equals(YesOrNo.Y);
    }
    public boolean isDinner(){
        return dinner.equals(YesOrNo.Y);
    }
}
