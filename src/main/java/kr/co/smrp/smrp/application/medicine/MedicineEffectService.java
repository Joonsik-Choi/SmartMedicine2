package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineEffect;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineEffectRepository;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfoRepository;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.ItemSeq;
import kr.co.smrp.smrp.dto.medicine.effect.MedicineEffectAskDto;
import kr.co.smrp.smrp.dto.medicine.effect.MedicineEffectTransfer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MedicineEffectService {
    MedicineEffectRepository medicineEffectRepository;
    MedicineInfoRepository medicineInfoRepository;
    static int count=0;

    PDDocument document;
    PDFTextStripper pdfStripper=new PDFTextStripper();
    @Autowired
    public MedicineEffectService(MedicineInfoRepository medicineInfoRepository, MedicineEffectRepository medicineEffectRepository) throws IOException {
        this.medicineInfoRepository=medicineInfoRepository;
        this.medicineEffectRepository=medicineEffectRepository;
    }
    public Message addListEffect(ArrayList<MedicineEffectAskDto> medicineEffectAskDtos) {   //약 효능 효과 데이터 넣기
        for(MedicineEffectAskDto medicineEffectAskDto: medicineEffectAskDtos){
            ArrayList<MedicineInfo> medicineInfos=medicineInfoRepository.findByItemList(medicineEffectAskDto.getItemSeq());
            if(medicineInfos.size()==0)continue;
            MedicineEffect medicineEffect=new MedicineEffect(medicineEffectAskDto);
           // medicineEffect.setMedicineInfo(medicineInfos.get(0));
            medicineEffectRepository.save(medicineEffect);
            if(medicineInfos.size()==1){
                medicineInfos.get(0).setMedicineEffect(medicineEffect);
            }
            else if(medicineInfos.size()>1){
                for(MedicineInfo medicineInfo: medicineInfos){
                    medicineInfo.setMedicineEffect(medicineEffect);
                }
            }
        }
        return Message.builder().resultCode(ResultCode.OK).build();
    }

    public String getEffect(Long id) {      //효능 효과 읽어오기
        Optional<MedicineEffect> medicineEffect=medicineEffectRepository.findById(id);
        if(medicineEffect.isPresent()){
            return medicineEffect.get().getEffect();
        }
        return "no data";
    }

    public String getEffect1111(ArrayList<ItemSeq> itemSeq) {    // 데이터 베이스에  효능 효과 추가
        String base="https://nedrug.mfds.go.kr/pbp/cmn/pdfDownload/";
        for(ItemSeq seq : itemSeq){
            ArrayList<MedicineInfo> medicineInfos=medicineInfoRepository.findByItemList(seq.getItemSeq());
            String effect=base+seq+"/EE";
            String usageCapacity=base+seq+"/UD";
            String precautions=base+seq+"/NB";
            MedicineEffect medicineEffect=MedicineEffect.builder()
                    .effect(effect)
                    .precautions(precautions)
                    .usageCapacity(usageCapacity)
                   // .medicineInfo(medicineInfos.get(0))
                    .build();
            medicineEffectRepository.save(medicineEffect);
            for(MedicineInfo medicineInfo: medicineInfos){
                medicineInfo.setMedicineEffect(medicineEffect);
            }
        }
        return "ok";
    }
    public String transferEffect(ArrayList<MedicineEffectTransfer> medicineEffectTransfers)  { // 약 효능 & 효과 url 을 문자열로 변환
        for(MedicineEffectTransfer medicineEffectTransfer :medicineEffectTransfers ){
            MedicineEffect medicineEffect=medicineEffectRepository.findById(Long.parseLong(medicineEffectTransfer.getMedicineEffectId())).get();
            if(medicineEffect.getId()==3218)continue;
            System.out.println(count+" : "+medicineEffect.getId()+"변환 중");
            if(medicineEffect.getEffect().substring(0, 4).equals("http")){
                System.out.println("effect 변환중");
                System.out.println(medicineEffect.getEffect().substring(0, 4));
                String text;
                text=getPdf(medicineEffect.getEffect());
                medicineEffect.setEffect(text);
            }
            if(medicineEffect.getPrecautions().substring(0, 4).equals("http")){
                System.out.println("Precautions 변환중");
                String text;
                text=getPdf(medicineEffect.getPrecautions());
                medicineEffect.setPrecautions(text);
            }
            if(medicineEffect.getUsageCapacity().substring(0, 4).equals("http")){
                System.out.println("Usage Capacity 변환중");
                String text;
                text=getPdf(medicineEffect.getUsageCapacity());
                medicineEffect.setUsageCapacity(text);
            }
            System.out.println(count+" : "+medicineEffect.getId()+"변환 완료");
            count++;
            medicineEffectRepository.save(medicineEffect);
        }
        return "ok";
    }

    public String getPdf(String url)  {         //pdf 파일 변환
        String s=url;
        try {
            URL url1=new URL(url);
            document= PDDocument.load(url1);//효능효과
            s= pdfStripper.getText(document);document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

}
