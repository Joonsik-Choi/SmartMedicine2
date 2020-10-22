package kr.co.smrp.smrp.dto.medicine.info;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInfoSmallResPon {
    private  Long id;
    private  String itemSeq;
    private  String itemName;
    private  String entpName;
    private  String itemImage;
    private String etcOtcName;
    private  String formCodeName;

    public MedicineInfoSmallResPon(MedicineInfo medicineInfo) {
        this.id = medicineInfo.getId();
        this.itemSeq = medicineInfo.getItemSeq();
        this.itemName = medicineInfo.getItemName();
        this.entpName = medicineInfo.getEntpName();
        this.itemImage = medicineInfo.getItemImage();
        this.etcOtcName = medicineInfo.getEtcOtcName();
        this.formCodeName = medicineInfo.getFormCodeName();
    }
}
