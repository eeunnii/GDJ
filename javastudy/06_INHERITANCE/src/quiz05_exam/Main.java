package quiz05_exam;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Exam exam1 = new Exam ("중간고사");
		exam1.setScore();  // 국,영, 수학 점수 0~100 사이의 랜덤 생성
		Exam exam2 = new Exam ("기말고사");
		exam2.setScore();	//국 영 수학 점수 0~100 사이 랜덤 생성
		
		Student student = new Student ("emily");
		
		student.setExam(exam1);
		student.setExam(exam2);
		
		student.info();
		
		// 학생이름 : emily
		// 중간고사 성적
		// 국어 : 50, 영어 : 50, 수학 : 50,총점 : 50, 평균 : 50
		// 기말고사 성적
		// 국어 : 50, 영어 : 50, 수학 : 50, 총점 : 50, 평균 : 50
		
		
		
		
		
		
		
		
		
	}

}
