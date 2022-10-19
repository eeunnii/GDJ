package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Board {
	private int boardNo;  // 원래 칼럼명과 맞춰줘야하지만 프레임워크가 도와줄예정이라 카멜페이스
	private String title;
	private String content;
	private Date createDate;
}
