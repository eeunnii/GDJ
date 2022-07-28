package fleid_pa;

public class SchoolMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		School school = new School();
		
		school.name = "경인중학교";
		
		
		
		Student s1 = new Student();
		s1.name = "정은지";
		s1.number = 24;
		
		Student s2 = new Student();
		s2.name = "이수한";
		s2.number = 21;
		
		

		school.arr[0] = s1;
		school.arr[1] = s2;
		
		
		System.out.println(school.arr[0].name);
		System.out.println(school.arr[0].number);
		System.out.println(school.arr[1].name);
		System.out.println(school.arr[1].number);
		
		
		
		
		
		
	}

}
