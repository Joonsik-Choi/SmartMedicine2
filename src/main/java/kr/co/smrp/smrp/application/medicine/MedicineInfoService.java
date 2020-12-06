package kr.co.smrp.smrp.application.medicine;

import com.sun.xml.bind.v2.TODO;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineEffectRepository;
import kr.co.smrp.smrp.dto.medicine.*;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfoRepository;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.Alarm.MedicineAlarmResponDto;
import kr.co.smrp.smrp.dto.medicine.deep.MedicineDeepModelAskDto;
import kr.co.smrp.smrp.dto.medicine.info.AddMedicineInfoAskDto;
import kr.co.smrp.smrp.dto.medicine.info.MedicineInfoRsponDTO;
import kr.co.smrp.smrp.dto.medicine.info.MedicineInfoSmallResPon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    @Transactional
    public ArrayList<MedicineInfoRsponDTO> findMedicineListOCR(String[] strings){ //약 봉투 OCR 필터링
        ArrayList<MedicineInfoRsponDTO> medicineInfoRsponDTOs=new ArrayList<>();
        char c;
        //Todo 이전 프로젝트에서 가져왔으므로 수정 요망
        for(String s: strings){
            System.out.println("----------------------------->"+ s);
            StringBuilder stringBuilder=new StringBuilder();
            for(int i=0;i<s.length();i++){
                c=s.charAt(i);
                if(c=='(' || c==')' ||  c==' '){
                    stringBuilder.append(c);
                    continue;
                }
                else if(c>0&&c<182){
                    continue;
                }
                stringBuilder.append(c);
            }
            if(stringBuilder.toString().length()<1){
                s=" ";
            }
            else {
                s = stringBuilder.toString();
            }
            System.out.println("1차 결과 s : "+s+ " 길이 : "+s.length());
            c=s.charAt(0);
            if(c>0&&c<182) {
                for (int i = 0; i < s.length(); i++) {
                    c = s.charAt(i);
                    if (c>0&&c<182) {
                        if(i+1==s.length()){
                            s="";
                        }
                        continue;
                    }
                    else {
                        s = s.substring(i);
                        System.out.println("수정 전 :"+s);
                        if (s.indexOf('(') != -1 || s.indexOf(' ') != -1) {
                            if (s.indexOf('(') != -1) {
                                s = s.substring(0, s.indexOf('('));
                            }
                            if(s.indexOf(' ')!=-1){
                                s = s.substring(0, s.indexOf(' '));
                            }
                        }
                        System.out.println("수정 후 :"+s);
                        break;
                    }
                }
            }
            System.out.println("2차 결과 s : "+s+ " 길이 : "+s.length());
            if(s.indexOf(' ')!=-1){
                s=s.substring(0, s.indexOf(' '));
            }
            if(s.indexOf('(')!=-1){
                s=s.substring(0, s.indexOf('('));
            }


            System.out.println("3차 결과 s : "+s+ " 길이 : "+s.length());
            if( s.length()<3){
                System.out.println(s+" 문자열 길이 : "+s.length());
                if(!s.contains("쿨정")) {
                    continue;
                }
                System.out.println("쿨정임");

            }
            System.out.println("4차 결과 s : "+s+ " 길이 : "+s.length());
            if(s.equals("일수")){
                System.out.println("버리는 약이름 :"+ s);
                continue;
            }
            System.out.println("5차 결과 s : "+s+ " 길이 : "+s.length());
            if(s.length()>9){
                s=s.substring(0, 8);
            }
            System.out.println("6차 결과 s : "+s+ " 길이 : "+s.length());
            ArrayList<MedicineInfo> medicineInfos= (ArrayList<MedicineInfo>) medicineInfoRepository.findAllByItemNameContaining(s);
            if(medicineInfos.size()==0){
                System.out.println("버리는 약이름 : "+ s );
                continue;
            }
            else{
                System.out.println("찾은 약이름  : "+ s );
                for(MedicineInfo medicineInfo:medicineInfos){
                    System.out.println(medicineInfo.getItemName()+" ");
                    MedicineInfoRsponDTO medicineInfoRsponDTO=new MedicineInfoRsponDTO(medicineInfo);
                    medicineInfoRsponDTOs.add(medicineInfoRsponDTO);
                }
            }
        }
        Collections.sort(medicineInfoRsponDTOs,(o1, o2) -> o1.getItemName().compareTo(o2.getItemName()));
        return medicineInfoRsponDTOs;
    }
    public void SearchMedicine(String[] s) {  // ocr 기능으로 약정보 보내기
        for (String ss: s){
            System.out.println(ss);
        }
    }
    @Transactional
    public ArrayList<MedicineInfoRsponDTO> searchDeepMedicine(MedicineDeepModelAskDto medicineDeepModelAskDto) { //딥러닝 모델 검색
        ArrayList<MedicineInfoRsponDTO> medicineInfoRsponDTOs=new ArrayList<>();
        String line_front=medicineDeepModelAskDto.getLineFront();
        String line_back=medicineDeepModelAskDto.getLineBack();
        String color=medicineDeepModelAskDto.getColor();
        String print_front=medicineDeepModelAskDto.getPrintFront();
        String print_back=medicineDeepModelAskDto.getPrintBack();
        String drug_shape=medicineDeepModelAskDto.getDrugShape();
        ArrayList<MedicineInfo> medicineInfos=medicineInfoRepository.findByMedicineDeep(drug_shape,color,line_front ,line_back,print_front,print_back);
        for(MedicineInfo medicineInfo:medicineInfos) {
            System.out.println(medicineInfo.getItemName() + " ");
            MedicineInfoRsponDTO medicineInfoRsponDTO = new MedicineInfoRsponDTO(medicineInfo);
            medicineInfoRsponDTOs.add(medicineInfoRsponDTO);
        }
            return  medicineInfoRsponDTOs;
    }
}
