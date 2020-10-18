package kr.co.smrp.smrp.dto.medicine;

import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import lombok.*;

import java.time.LocalDate;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SumMedInfo {
    private Long id;
    private String imageUrl;
    private String itemSeq;
    private String itemName;
    private String entpName;
    private LocalDate createdAt;
    public SumMedInfo(RegMedicine regMedicine){
        id=regMedicine.getId();
        imageUrl=regMedicine.getMedicineInfo().getItemImage();
        itemSeq=regMedicine.getMedicineInfo().getItemSeq();
        itemName=regMedicine.getMedicineInfo().getItemName();
        entpName=regMedicine.getMedicineInfo().getEntpName();
        createdAt= LocalDate.from(regMedicine.getCreatedAt());
    }
}
