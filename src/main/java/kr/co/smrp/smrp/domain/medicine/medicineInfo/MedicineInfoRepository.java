package kr.co.smrp.smrp.domain.medicine.medicineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MedicineInfoRepository extends JpaRepository<MedicineInfo,Long> {
    @Query(value="select * from MEDICINE_INFO a where a.item_seq = :itemseq", nativeQuery=true)
    ArrayList<MedicineInfo> findByItemList(@Param("itemseq")String itemseq);

    Optional<MedicineInfo> findByItemSeq(String medicineId);
    @Query(value="select * from MEDICINE_INFO a where a.item_name like :itemName%", nativeQuery=true)
    ArrayList<MedicineInfo> findByItemNameMethod1(@Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod0(@Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where (a.line_front in (:line) or a.line_back in (:line)) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod1(@Param("line")ArrayList<String> line, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where (a.formula in(:formula))and item_name like %:itemName%", nativeQuery=true)//
    List<MedicineInfo> findMethod2(@Param("formula")ArrayList<String> formula, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where((a.formula in(:formula)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod3(@Param("formula")ArrayList<String> formula, @Param("line")ArrayList<String> line, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where (a.color_class1 in (:color) or a.color_class2 in (:color)) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod4(@Param("color")ArrayList<String> color, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where((a.color_class1 in (:color) or a.color_class2 in (:color)) and  (a.line_front in (:line) or a.line_back in (:line))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod5(@Param("color")ArrayList<String> color, @Param("line")ArrayList<String> line, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where((a.color_class1 in (:color) or a.color_class2 in (:color)) and  (a.formula in(:formula))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod6(@Param("color")ArrayList<String> color, @Param("formula")ArrayList<String> formula, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where((a.color_class1 in (:color) or a.color_class2 in (:color)) and  (a.formula in(:formula)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod7(@Param("color")ArrayList<String> color, @Param("formula")ArrayList<String> formula, @Param("line")ArrayList<String> line, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and item_name like %:itemName%)", nativeQuery=true)
    List<MedicineInfo> findMethod8(@Param("shape")ArrayList<String> shape, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod9(@Param("shape")ArrayList<String> shape, @Param("line")ArrayList<String> line,@Param("itemName") String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.formula in(:formula))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod10(@Param("shape")ArrayList<String> shape, @Param("formula")ArrayList<String> formula,@Param("itemName") String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.formula in(:formula)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod11(@Param("shape")ArrayList<String> shape, @Param("formula")ArrayList<String> formula, @Param("line")ArrayList<String> line,@Param("itemName") String itemName);

    @Query(value="select * from MEDICINE_INFO a where  ((a.drug_shape in (:shape)) and (a.color_class1 in (:color) or a.color_class2 in (:color))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod12(@Param("shape")ArrayList<String> shape, @Param("color")ArrayList<String> color, String itemName);

    @Query(value="select * from MEDICINE_INFO a where  ((a.drug_shape in (:shape)) and (a.color_class1 in (:color) or a.color_class2 in (:color)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod13(@Param("shape")ArrayList<String> shape, @Param("color")ArrayList<String> color, @Param("line")ArrayList<String> line,@Param("itemName") String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.color_class1 in (:color) or a.color_class2 in (:color)) and (a.formula in(:formula))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod14(@Param("shape")ArrayList<String> shape, @Param("color")ArrayList<String> color, @Param("formula")ArrayList<String> formula, @Param("itemName")String itemName);

    @Query(value="select * from MEDICINE_INFO a where ((a.drug_shape in (:shape)) and (a.color_class1 in (:color) or a.color_class2 in (:color)) and (a.formula in(:formula)) and (a.line_front in (:line) or a.line_back in (:line))) and item_name like %:itemName%", nativeQuery=true)
    List<MedicineInfo> findMethod15(@Param("shape")ArrayList<String> shape, @Param("color")ArrayList<String> color,@Param("formula") ArrayList<String> formula,@Param("line") ArrayList<String> line, @Param("itemName")String itemName);
}