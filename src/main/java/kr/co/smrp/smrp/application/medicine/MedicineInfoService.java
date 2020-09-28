package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineEffect;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineEffectRepository;
import kr.co.smrp.smrp.dto.medicine.*;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfoRepository;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineInfoService {
    private MedicineInfoRepository medicineInfoRepository;
    private MedicineEffectRepository medicineEffectRepository;

    PDDocument document;
    PDFTextStripper pdfStripper=new PDFTextStripper();


    public MedicineInfoService(MedicineInfoRepository medicineInfoRepository, MedicineEffectRepository medicineEffectRepository) throws IOException {
        this.medicineInfoRepository=medicineInfoRepository;
        this.medicineEffectRepository=medicineEffectRepository;
    }

    public void add(AddMedicineInfoAskDto addMedicineInfoAskDto) { //약정보 추가
        MedicineInfo medicineInfo=addMedicineInfoAskDto.toEntity();
        medicineInfoRepository.save(medicineInfo);
    }
  public List<MedicineInfoRsponDTO> getConMedicineInfo(ConMedicineAskDto conMedicineAskDto){ //약 선태사항 검색
        List<MedicineInfo> medicineInfos=new ArrayList<>();
        List<MedicineInfoRsponDTO> medicineInfoRsponDTOS=new ArrayList<>();
      String num=conMedicineAskDto.getConditionNum();
      String itemName=conMedicineAskDto.getMedicineName();
      ArrayList<String> line=conMedicineAskDto.getLine();
      ArrayList<String> formula=conMedicineAskDto.getFormula();
      ArrayList<String> color=conMedicineAskDto.getColor();
      ArrayList<String> shape=conMedicineAskDto.getShape();
      switch (num){
          case "0000":
              medicineInfos=medicineInfoRepository.findMethod0(itemName);
              break;
          case "0001":
              medicineInfos=medicineInfoRepository.findMethod1(line, itemName);
              break;
          case "0010":
              medicineInfos= medicineInfoRepository.findMethod2(formula,itemName);
              break;
          case "0011":
              medicineInfos= medicineInfoRepository.findMethod3(formula,line,itemName);
              break;
          case "0100":
              medicineInfos=medicineInfoRepository.findMethod4(color,itemName);
              break;
          case "0101":
              medicineInfos=medicineInfoRepository.findMethod5(color,line,itemName);
              break;
          case "0110":
              medicineInfos=medicineInfoRepository.findMethod6(color,formula,itemName);
              break;
          case "0111":
              medicineInfos=medicineInfoRepository.findMethod7(color,formula,line,itemName);
              break;
          case "1000":
              medicineInfos=medicineInfoRepository.findMethod8(shape,itemName);
              break;
          case "1001":
              medicineInfos=medicineInfoRepository.findMethod9(shape,line,itemName);
              break;
          case "1010":
              medicineInfos=medicineInfoRepository.findMethod10(shape,formula,itemName);
              break;
          case "1011":
              medicineInfos=medicineInfoRepository.findMethod11(shape,formula,line,itemName);
              break;
          case "1100":
              medicineInfos=medicineInfoRepository.findMethod12(shape,color,itemName);
              break;
          case "1101":
              medicineInfos=medicineInfoRepository.findMethod13(shape,color,line,itemName);
              break;
          case "1110":
              medicineInfos=medicineInfoRepository.findMethod14(shape,color,formula,itemName);
              break;
          case "1111":
              medicineInfos=medicineInfoRepository.findMethod15(shape,color,formula,line,itemName);
              break;
          default:
              System.out.println("오류");
      }
      for(MedicineInfo medicineInfo : medicineInfos){
          MedicineInfoRsponDTO medicineInfoRsponDTO=new MedicineInfoRsponDTO(medicineInfo);
          medicineInfoRsponDTOS.add(medicineInfoRsponDTO);
      }
        return medicineInfoRsponDTOS;
  } //약 조건 검사

    public MedicineInfoRsponDTO getMedicineInfo(String itemSeq) { //약 1건 상제 정보 보기
        Optional<MedicineInfo>  medicineInfo=medicineInfoRepository.findByItemSeq(itemSeq);
        MedicineInfoRsponDTO medicineInfoRsponDTO=new MedicineInfoRsponDTO(medicineInfo.get());
        return medicineInfoRsponDTO;
    }

    public Message addList(ArrayList<MedicineInfo> medicineInfos) {
        for(MedicineInfo medicineInfo1 :medicineInfos)
            medicineInfoRepository.save(medicineInfo1);
        return Message.builder().resultCode(ResultCode.OK).build();
    }

    public Message addListEffect(ArrayList<MedicineEffectAskDto> medicineEffectAskDtos) {
        for(MedicineEffectAskDto medicineEffectAskDto: medicineEffectAskDtos){
            ArrayList<MedicineInfo> medicineInfos=medicineInfoRepository.findByItemList(medicineEffectAskDto.getItemSeq());
            if(medicineInfos.size()==0)continue;
            MedicineEffect medicineEffect=new MedicineEffect(medicineEffectAskDto);
            medicineEffect.setMedicineInfo(medicineInfos.get(0));
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

    public String getEffect(Long id) {
        Optional<MedicineEffect> medicineEffect=medicineEffectRepository.findById(id);
        if(medicineEffect.isPresent()){
            return medicineEffect.get().getEffect();
        }
        return "no data";
    }

    public String getEffect1111(ArrayList<ItemSeq> itemSeq) {
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
                                                .medicineInfo(medicineInfos.get(0))
                                                .build();
            medicineEffectRepository.save(medicineEffect);
            for(MedicineInfo medicineInfo: medicineInfos){
                medicineInfo.setMedicineEffect(medicineEffect);
            }
        }
        return "ok";
    }
    public String transferEffect() throws IOException {
        ArrayList<MedicineEffect> medicineEffects= (ArrayList<MedicineEffect>) medicineEffectRepository.findAll();
        for(MedicineEffect medicineEffect :medicineEffects){
            if(medicineEffect.getEffect().substring(0, 5).contains("http")){
                String text;
                text=getPdf(medicineEffect.getEffect());
                medicineEffect.setEffect(text);
            }
            if(medicineEffect.getPrecautions().substring(0, 5).contains("http")){
                String text;
                text=getPdf(medicineEffect.getPrecautions());
                medicineEffect.setPrecautions(text);
            }
            if(medicineEffect.getUsageCapacity().substring(0, 5).contains("http")){
                String text;
                text=getPdf(medicineEffect.getUsageCapacity());
                medicineEffect.setUsageCapacity(text);
            }
        }
        return "ok";
    }

    public String getPdf(String url) throws IOException {
        document=PDDocument.load(new URL(url));//효능효과
        String s=pdfStripper.getText(document);
        document.close();
        return s;
    }


}
