package ex09_this;

public class School {
	
	
	
	
	//필드 
	private Student[] students;
	private int idx;             // students 배열의 인덱스, students배열에 저장된 학생수와 같다
	

	//생성자 
	public School(int cnt){
	   students = new Student[cnt];
	}
	
	//메소드 
	public void addStudent(Student student) {
		students[idx++]=student;
		
		
		if(idx == students.length) {
			System.out.println("꽉참 자리없음");
			return;
		} 
				students[idx++]=	student;
				
	}
	
	
	public void printStudents() {
		for(int i =0; i<idx; ++i) {
			System.out.println(students[1].getName()+","+students[i].getName());
		}
	}
	
	
	
	
}
