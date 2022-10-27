package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Post;

public class PostDao {
	
	//SqlSesstionFactiory 빌드 (가장먼저 해주어야함)
	private SqlSessionFactory factory;
																																
	// Dao를 호출하면 factory도 만들어져서 메소드 호출 가능해짐
	// singleton pattern
	private static PostDao dao = new PostDao();
	private PostDao() {
		
		try {
			// SqlSessionFactory 빌드	
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static PostDao getInstance() {
		return dao;
	}
	
	public List<Post> selectAllPosts(){
		SqlSession ss = factory.openSession();
		List<Post> posts = ss.selectList("mybatis.mapper.post.selectAllPosts");
		ss.close();
		return posts;
	}
	
	public int countAllPosts() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne("mybatis.mapper.post.countAllPosts");
		ss.close();
		return count;
	}
	
	public int insertPost(Post post) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("mybatis.mapper.post.insertPost", post);
		if(result > 0) {
			ss.commit(); // 성공하면 커밋
		}
		ss.close();
		return result;
	}
	
	

}
