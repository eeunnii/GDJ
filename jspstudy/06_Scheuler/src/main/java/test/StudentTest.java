package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import domain.Student;
import repository.StudentDao;

class StudentTest {
	
	
	/* 순서대로 전체 테스트 풀기 
	 * 1. 삽입 : 테스터,50,50,50
	 * 2. 목록
	 * 3. 상세
	 * 4. 수정
	 * 	: 테스터2, 60,60,60
	 * 5. 삭제 테스트 
	 */
	@Test
	void test() {
		fail("Not yet implemented");
	}
	//@Before
	//StudentText 클래스 실행 시 한 번만 먼저 실행
	//static 처리 필요
	
	
	@Before
	void 삽입테스트() {
		Student s = Student.builder()
				.name("테스터")
				.kor(50)
				.eng(50)
				.math(50)
				.build();
		assertEquals(1, StudentDao.getInstance().insertStudent(s));
	}
	
	@Test
	public void 목록테스트() {
		assertEquals(6, StudentDao.getInstance().selectAllStudents().size());
	}
	@Test
	public void 상세테스트() {
		assertNotNull(StudentDao.getInstance().selectAllStudents());
	}
	
	// @AfterClass
	// StudentTest 클래스 실행 시 한 번만 마지막으로 실행
	// static 처리 필요
	@AfterClass
	public static void 삭제테스트() {
		assertEquals(1, StudentDao.getInstance().deleteStudent(1));
	}

}
