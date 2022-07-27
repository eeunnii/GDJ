package ex01_field;

public class SchoolMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		School school = new School();
		
		
		school.name = "경인중학교";
		Student student1 =  new Student() ;             //nullpointexception
		student1.stuNo = "45";
		student1.name = "정은지";
		
		Student student2 = new Student();
		student2.name = "전지현";
		student2.stuNo = "545";
		
		school.students[0] = student1;
		school.students[0] = student2;
		
		for(int i =0; i<school.students.length;	i++);
//		System.out.println(school.students[i].stuNo);
//		System.out.println(school.students[i].name);
		
		//모든 객체들은 참조타입이며 안에 내용이 없고 주소만 있다 
		
		
	}

}

//멀 연습하라는걸까 