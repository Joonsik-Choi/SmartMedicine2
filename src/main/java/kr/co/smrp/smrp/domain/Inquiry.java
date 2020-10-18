package kr.co.smrp.smrp.domain;

import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inquiry {
    @Id
    @GeneratedValue
    @Column(name = "INQUIRY_ID")
    Long id;
    @ManyToOne
    UserInfo userInfo;
    String content;

}
