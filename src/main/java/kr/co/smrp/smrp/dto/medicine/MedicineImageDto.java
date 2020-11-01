package kr.co.smrp.smrp.dto.medicine;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineImageDto {
    private MultipartFile pront;
    private MultipartFile back;
}
