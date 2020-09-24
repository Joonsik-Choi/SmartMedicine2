package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.ConMedicineAskDto;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfoRepository;
import kr.co.smrp.smrp.dto.medicine.AddMedicineInfoAskDto;
import kr.co.smrp.smrp.dto.medicine.MedicineInfoRsponDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineInfoService {
    private MedicineInfoRepository medicineInfoRepository;
    public MedicineInfoService(MedicineInfoRepository medicineInfoRepository){
        this.medicineInfoRepository=medicineInfoRepository;
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
}
