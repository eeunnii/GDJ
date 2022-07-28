package hitv;

public class ClassStudyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ClassStudy classStudy = new ClassStudy();
		
		
		
		System.out.println(classStudy.name);
		
		
		classStudy.name = "정은지";
		
		
		System.out.println(classStudy.isVip);
		
		
		classStudy.point = 10545;
		
	
		classStudy.isVip = (classStudy.point >= 1000);
		
		System.out.println(classStudy.isVip);
		
		
		
		
		
		
	}

}
