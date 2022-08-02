package quiz05_exam;



public class Exam {

	private String ExamName;
	private int kor;
	private int eng;
	private int mat;
	
	
	
	
	
	public Exam(String examName) {
		super();
		ExamName = examName;
		
	}




	public void setScore () {
		
		kor= (int)(Math.random()*100)+1;
		eng= (int)(Math.random()*100)+1;
		mat= (int)(Math.random()*100)+1;
		
		
		
		
	}
	
	
	public void examInfo () {
		System.out.println(ExamName + "성적");
		System.out.println("국어 : "+kor+"영어 : "+eng+"수학 : "+mat);
		int total = kor +eng + mat;
		System.out.println("총점:"+total+",평균"+(total/3.0));
	}
	
}
