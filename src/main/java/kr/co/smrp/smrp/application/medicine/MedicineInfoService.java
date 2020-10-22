package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineEffectRepository;
import kr.co.smrp.smrp.dto.medicine.*;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfoRepository;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.info.AddMedicineInfoAskDto;
import kr.co.smrp.smrp.dto.medicine.info.MedicineInfoRsponDTO;
import kr.co.smrp.smrp.dto.medicine.info.MedicineInfoSmallResPon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineInfoService {
    private MedicineInfoRepository medicineInfoRepository;
    private MedicineEffectRepository medicineEffectRepository;
    public MedicineInfoService(MedicineInfoRepository medicineInfoRepository, MedicineEffectRepository medicineEffectRepository) throws IOException {
        this.medicineInfoRepository=medicineInfoRepository;
        this.medicineEffectRepository=medicineEffectRepository;
    }

    public void add(AddMedicineInfoAskDto addMedicineInfoAskDto) { //약정보 추가
        MedicineInfo medicineInfo=addMedicineInfoAskDto.toEntity();
        medicineInfoRepository.save(medicineInfo);
    }
  public List<MedicineInfoSmallResPon> getConMedicineInfo(ConMedicineAskDto conMedicineAskDto){ //약 선태 사항 검색
        List<MedicineInfo> medicineInfos=new ArrayList<>();
        List<MedicineInfoSmallResPon> medicineInfoSmallResPons=new ArrayList<>();
      String num=conMedicineAskDto.getConditionNum();
      String itemName="%"+conMedicineAskDto.getMedicineName()+"%";
      ArrayList<String> line=conMedicineAskDto.getLine();
      ArrayList<String> formula=conMedicineAskDto.getFormula();
      ArrayList<String> color=conMedicineAskDto.getColor();
      ArrayList<String> shape=conMedicineAskDto.getShape();
      System.out.println(num);
      switch (num){
          case "0000":  //약 이름만 검색
              System.out.println(itemName);
              medicineInfos=medicineInfoRepository.findMethod0(itemName);
              System.out.println(medicineInfos.size());
              break;
          case "0001":   //이름 + 분할선 검색
              medicineInfos=medicineInfoRepository.findMethod1(line,itemName);
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
          medicineInfoSmallResPons.add(new MedicineInfoSmallResPon(medicineInfo));
      }
        return medicineInfoSmallResPons;
  } //약 조건 검사
    @Transactional
    public MedicineInfoRsponDTO getMedicineInfo(String itemSeq) { //약 1건 상제 정보 보기
        List<MedicineInfo>  medicineInfo=medicineInfoRepository.findByItemSeq(itemSeq);
        MedicineInfoRsponDTO medicineInfoRsponDTO=new MedicineInfoRsponDTO(medicineInfo.get(0));
        return medicineInfoRsponDTO;
    }

    public Message addList(ArrayList<MedicineInfo> medicineInfos) { //
        for(MedicineInfo medicineInfo1 :medicineInfos)
            medicineInfoRepository.save(medicineInfo1);
        return Message.builder().resultCode(ResultCode.OK).build();
    }


    public MedicineInfoRsponDTO findMedicineOcr(String[] medicineLogo) { // 알약 인식 기능
       String[] code=new String[2];
       ArrayList<MedicineInfo> medicineInfos=new ArrayList<>();
       code[0]=medicineLogo[0];
       code[1]=medicineLogo[1];
       medicineInfos=medicineInfoRepository.findMethodOcr(code[0], code[1]);
       if(medicineInfos.size()>0) {
            return new MedicineInfoRsponDTO(medicineInfos.get(0));
       }
       return MedicineInfoRsponDTO.builder().build();
    }

    public void SearchMedicine(String[] s) {  // ocr 기능으로 약정보 보내기
        for (String ss: s){
            System.out.println(ss);
        }
    }
}
