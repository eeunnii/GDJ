package domain;


//DTO의 역할 
//데이터베이스가 데이터를 넘겨줄때 이건 DTO

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data        // @Getter Setter ToSting
@NoArgsConstructor
@AllArgsConstructor

@Builder



//컨텍트 테이블 정보르 ㄹ 1:!로 읽어옴
public class ContactDTO {
		private int contact_no;
		private String name;
		private String tel;
		private String email;
		private Date reg_date;

};
