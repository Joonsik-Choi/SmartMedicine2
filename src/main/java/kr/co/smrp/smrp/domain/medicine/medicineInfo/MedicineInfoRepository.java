package kr.co.smrp.smrp.domain.medicine.medicineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
public interface MedicineInfoRepository extends JpaRepository<MedicineInfo,Long> {
    @Query(value="select * from MEDICINE_INFO a where a.item_seq = :itemseq", nativeQuery=true)
    ArrayList<MedicineInfo> findByItemList(@Param("itemseq")String itemseq);

    List<MedicineInfo> findByItemSeq(String medicineId);
    List<MedicineInfo> findAllByItemNameContaining(String ItemName);

    @Query(value="select *  " +
            "from medicine_info mi " +
            "where drug_shape =:shape " +
            " and (color_class1 like CONCAT ('%',:color,'%') or color_class2 like  CONCAT ('%',:color,'%') )" +
            " and (line_front like  CONCAT ('%',:line_front,'%') AND line_back like  CONCAT ('%',:line_back,'%'))" +
            " and (print_front like  CONCAT ('%',:print_front,'%') AND print_back  like  CONCAT ('%',:print_back,'%') )" +
            " order by item_name "
            +"limit 10 ", nativeQuery=true)
    ArrayList<MedicineInfo> findByMedicineDeep(@Param("shape")String shape, @Param("color")String color, @Param("line_front")String line_front,
                                               @Param("line_back")String line_back, @Param("print_front")String print_front,  @Param("print_back")String print_back);

    @Query(value="select * from MEDICINE_INFO a where a.item_name like :itemName", nativeQuery=true)
    ArrayList<MedicineInfo> findByItemNameMethod1(@Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO where item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod0(@Param("itemName")String itemName);  //이름검색


    @Query(value="select * from MEDICINE_INFO a where (a.line_front in (:line) or a.line_back in (:line)) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod1(@Param("line")ArrayList<String> line, @Param("itemName")String itemName); //이름+분할선 검색

   
    @Query(value="select * from MEDICINE_INFO a where (a.formula in(:formula))and item_name like :itemName", nativeQuery=true)//
    List<MedicineInfo> findMethod2(@Param("formula")ArrayList<String> formula, @Param("itemName")String itemName);
    

    @Query(value="select * from MEDICINE_INFO a where((a.formula in(:formula)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod3(@Param("formula")ArrayList<String> formula, @Param("line")ArrayList<String> line, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where (a.color_class1 in (:color) or a.color_class2 in (:color)) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod4(@Param("color")ArrayList<String> color, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where((a.color_class1 in (:color) or a.color_class2 in (:color)) and  (a.line_front in (:line) or a.line_back in (:line))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod5(@Param("color")ArrayList<String> color, @Param("line")ArrayList<String> line, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where((a.color_class1 in (:color) or a.color_class2 in (:color)) and  (a.formula in(:formula))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod6(@Param("color")ArrayList<String> color, @Param("formula")ArrayList<String> formula, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where((a.color_class1 in (:color) or a.color_class2 in (:color)) and  (a.formula in(:formula)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod7(@Param("color")ArrayList<String> color, @Param("formula")ArrayList<String> formula, @Param("line")ArrayList<String> line, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and item_name like :itemName)", nativeQuery=true)
    List<MedicineInfo> findMethod8(@Param("shape")ArrayList<String> shape, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod9(@Param("shape")ArrayList<String> shape, @Param("line")ArrayList<String> line,@Param("itemName") String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.formula in(:formula))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod10(@Param("shape")ArrayList<String> shape, @Param("formula")ArrayList<String> formula,@Param("itemName") String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.formula in(:formula)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod11(@Param("shape")ArrayList<String> shape, @Param("formula")ArrayList<String> formula, @Param("line")ArrayList<String> line,@Param("itemName") String itemName);

    @Query(value="select * from MEDICINE_INFO a where  ((a.drug_shape in (:shape)) and (a.color_class1 in (:color) or a.color_class2 in (:color))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod12(@Param("shape")ArrayList<String> shape, @Param("color")ArrayList<String> color, String itemName);

    @Query(value="select * from MEDICINE_INFO a where  ((a.drug_shape in (:shape)) and (a.color_class1 in (:color) or a.color_class2 in (:color)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod13(@Param("shape")ArrayList<String> shape, @Param("color")ArrayList<String> color, @Param("line")ArrayList<String> line,@Param("itemName") String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.color_class1 in (:color) or a.color_class2 in (:color)) and (a.formula in(:formula))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod14(@Param("shape")ArrayList<String> shape, @Param("color")ArrayList<String> color, @Param("formula")ArrayList<String> formula, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.color_class1 in (:color) or a.color_class2 in (:color)) and (a.formula in(:formula)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like :itemName", nativeQuery=true)
    List<MedicineInfo> findMethod15(@Param("shape")ArrayList<String> shape, @Param("color")ArrayList<String> color,@Param("formula") ArrayList<String> formula,@Param("line") ArrayList<String> line, @Param("itemName")String itemName);
    @Query(value="select * from MEDICINE_INFO a where (print_front like :code1 and print_back like :code2) or (print_front like :code2 and print_back like :code1)", nativeQuery=true)
    ArrayList<MedicineInfo> findMethodOcr(@Param("code1")String code1,@Param("code2") String code2);
}