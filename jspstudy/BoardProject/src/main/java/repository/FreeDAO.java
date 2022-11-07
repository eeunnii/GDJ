package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Free;

public class FreeDAO {
	
		

	//SqlSesstionFactiory 빌드 (가장먼저 해주어야함)
		private SqlSessionFactory factory;

		// Dao를 호출하면 factory도 만들어져서 메소드 호출 가능해짐
		// singleton pattern
		private static FreeDAO dao = new FreeDAO();
		private FreeDAO() {
			try {
				// SqlSessionFactory 빌드
				String resource = "mybatis/config/mybatis-config.xml";
				InputStream in = Resources.getResourceAsStream(resource);
				factory = new SqlSessionFactoryBuilder().build(in);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		public static FreeDAO getInstance() {
			return dao;
		}
		
		// 이렇게도 가능.~
		String mapper = "mybatis.mapper.Free.";  // 마지막 마침표 유지하기
		
		public List<Free> selectAllFree() {
			SqlSession ss = factory.openSession();
			List<Free> frees = ss.selectList(mapper+"selectAllFree");
			ss.close();
			return frees;
			
	}
		
		public int insertFree(Free free) {
			System.out.println("다오도착!");
			SqlSession ss = factory.openSession(false);
			int result = ss.insert(mapper+"insertFree", free);
			if(result>0) {
				ss.commit();
			}
			ss.close();
			return result;
		}
		
		// 4. 게시글 삭제 
		public int deleteFree(int freeNo) {
			SqlSession ss = factory.openSession(false);
			int result = ss.delete(mapper+"deleteFree",freeNo); // 아이디는 해당 메소드 이름으로 
			if(result>0) {
				ss.commit();
			}
			ss.close();
			return result;
		}
		
		// 상세보기
		public Free selectFreeByNo(int freeNo) {
			SqlSession ss = factory.openSession();
			Free free = ss.selectOne(mapper+"selectFreeByNo", freeNo);  // boardNo를 파라미터로 전달
			ss.close();
			return free;

		}
		
		// 5. 게시글 수정
		public int updateFree(Free free) {
			SqlSession ss = factory.openSession(false); // UPDATE(커밋이필요한 경우)
			int result = ss.update(mapper+"updatdFree", free);
			if(result>0) {
				ss.commit();
			}
			ss.close();
			return result;
		}
		
		
		
}
