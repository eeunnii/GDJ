package ex09_this;

public class SchoolMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Student student1 = new Student();
		
//		System.out.println(student);
//		student.printThis();
//		
		
		//student는 this는 같다
		//this.stuNo
		//this.naem
		
		student1.setStuNo("1125");
		student1.setName("전지현");

		
		Student student2 = new Student("1101", "정우성");
//		
		School school = new School(2);
		school.addStudent(student1);
		school.addStudent(student2);
		school.addStudent(student1);
		school.printStudents();
		
		
	}

}
