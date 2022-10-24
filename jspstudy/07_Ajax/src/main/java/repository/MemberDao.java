package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDao {
	
	//SqlSesstionFactiory 빌드 (가장먼저 해주어야함)
	private SqlSessionFactory factory;

	// Dao를 호출하면 factory도 만들어져서 메소드 호출 가능해짐
	// singleton pattern
	private static MemberDao dao = new MemberDao();
	private MemberDao() {
		try {
			// SqlSessionFactory 빌드
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static MemberDao getInstance() {
		return dao;
	}
	
	// 이렇게도 가능.~
	String mapper = "mybatis.mapper.member.";  // 마지막 마침표 유지하기
	
	
	
	//회원목록
	public List<Member> selectAllMember() {
			SqlSession ss = factory.openSession();
			List<Member> members = ss.selectList(mapper+"selectAllMember");
			ss.close();
			return members;
	}
	
	public int selectAllMemberCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(mapper+"selectAllMemberCount");
		ss.close();
		return count;
	}
	
	public Member selectMemberByNo(int memberNo) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne(mapper + "selectMemberByNo", memberNo);
		ss.close();
		return member;
	}
	
	//회원등록
	public int insertMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertMember", member);
		if(result>0) {
			ss.commit();
		}
		return result;
	}
	
}