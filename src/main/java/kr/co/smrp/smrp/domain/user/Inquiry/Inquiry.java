package kr.co.smrp.smrp.domain.user.Inquiry;

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
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_INFO_ID")
    private UserInfo userInfo;
    @Column(length = 500)
    private String content;

}
