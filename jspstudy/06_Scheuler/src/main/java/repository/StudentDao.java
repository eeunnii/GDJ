package repository;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class StudentDao {
	
	//SqlSesstionFactiory 빌드 (가장먼저 해주어야함)
	private SqlSessionFactory factory;

	// Dao를 호출하면 factory도 만들어져서 메소드 호출 가능해짐
	// singleton pattern
	private static StudentDao dao = new StudentDao();
	private StudentDao() {
		
		try {
			// SqlSessionFactory 빌드
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static StudentDao getInstance() {
		return dao;
	}
	
}