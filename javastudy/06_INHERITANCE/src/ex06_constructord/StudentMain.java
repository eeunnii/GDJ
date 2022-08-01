package ex06_constructord;

public class StudentMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Student student = new Student("tom","goodee");
		
		System.out.println(student.getName());
		System.out.println(student.getSchool());
		
		Alba alba = new Alba("jessica", "seoul univ", "library");
		System.out.println(alba.getName());
		System.out.println(alba.getSchool());
		System.out.println(alba.getCompany());
		
	}

}
