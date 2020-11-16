package kr.co.smrp.smrp.dto.medicine;

import lombok.*;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineImageDto {
    List<MultipartFile> multipartFileList;
}
