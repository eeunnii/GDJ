package com.gdu.app9.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/* @PropertySource
 * 안녕, 난 프로퍼티 파일을 참조할 수 있는 애너테이션이야
 * 
 * jsp에서는 xml에서 읽어들이는 태그를 넣은게 두번째 방법
 * 자바 애너테이션으로 읽어들이기
 */
@PropertySource(value={"classpath:mybatis/config/db.properties"})

@EnableAspectJAutoProxy
@EnableTransactionManagement
@Configuration
public class DBConfig {
	
	
	//동일한 프로퍼티가 여러개 있는 경우 여러번 적어야하한다.
	// 이를 편하게 해주는 뭔가잇음(프로퍼티파일 정리해주는거) 야물파일
	

	// db.properties 파일을 읽어서 변수에 저장하기
	// ${프로퍼티명}
	@Value(value = "${hikari.driver}") 
	private String driver;
	
	@Value(value = "${hikari.url}")
	private String url;
	
	@Value(value ="${hikari.username}")
	private String username;
	
	@Value(value="${hikari.password")
	private String password;
	
	@Value(value="${mapper.locations}")
	private String mapperLocations;   // 이건 무슨 코드지
	
	@Value(value="${config.location}")
	private String configLocation;
	
	// HikariConfig
	@Bean
	public HikariConfig config() {
		HikariConfig config = new HikariConfig();
			config.setDriverClassName(driver);
			config.setJdbcUrl(url);
			config.setUsername(username);
			config.setPassword(password);
			return config;
	}
	
	// HikariDataSource  -- 단독적인 cp
	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		return new HikariDataSource(config());
	}
	
	// Mybatis셋팅 
	
	
	
	// SqlSessionFactory  // mybatis의 옛날 이름이 ibatis임 
	@Bean 
	public SqlSessionFactory factory() throws Exception{ // 예외 던지기  . . 왜 던지지??
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));  // 매퍼 여러개 나올 예정이라 getResources(복수형) : "mybatis/mapper/*.xml"
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation)); // 마이바티스 Config의 위치 :  "mybatis/config/mabatis-config.xml"
		return bean.getObject();

	}
	 
	
	// SqlSessionTmplate
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception{   // mybatis수행할 때 얘가 다함
		return new SqlSessionTemplate(factory());
	}
	
	
	// 트랜잭션 처리를 위한 TransactionManager를 Bean으로 등록한다.
	@Bean 
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());  // 트랜잭션할 때도 dataSource() 가 필요함
	}
		
	
	
	
}
