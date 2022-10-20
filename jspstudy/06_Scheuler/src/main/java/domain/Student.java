package domain;

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
public class Student {
	private int stuNo;  // 원래 칼럼명과 맞춰줘야하지만 프레임워크가 도와줄예정이라 카멜페이스
	private String name;
	private int kor, eng, math;
	private double ave;
	private String grade;
}
