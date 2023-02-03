package scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/*
 	ServletContextListener 인터페이스
 	
 	1. 웹 애플리케이션(프로젝트)의 LifeCycle을 가진다. (웹 애플리케이션과 함깨 동작한다.)
 	2. contextInitalilzed() : 웹 애플리케이션 시작할 때 동작함 
 	3. contextDestroyed() : 웹 애플리케이션 종료할 때 동작 
 */
@WebListener // 나는 리스너입니다 (지우기 금지)
public class StudentListener implements ServletContextListener {
	
	// field 
	private Scheduler scheduler;
	
	
	// contructor 생성자
	// Scheduler scheduler 생성
    public StudentListener() {
        // http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/tutorial-lesson-01.html 회색박스 첫 두줄
    	SchedulerFactory factory = null;
    	try {
    		factory = new StdSchedulerFactory();
    		scheduler = factory.getScheduler();  // 리스너가 동작하면 스케줄러가 만들어지는거임
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	
    // contextDestroyed() 메소드 
    // Scheduler 종료 
    public void contextDestroyed(ServletContextEvent arg0)  { 
         try {
        	 if(scheduler != null) {
        		 scheduler.shutdown();
        	 }
         }catch (Exception e) {
			e.printStackTrace();
		}
    }

	// contextInitialized() 메소드
    // Jop, Trigger 생성
    // Scheduler에 Job과 Trigger 등록 
    // scheduler의 시작
    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	/*
    		Cron Expression(크론식) -- www.cronMaber.com
    		org  - 기관 : 계속 있음
    		.com - 회사 : 언제 어떻게 없어지거나 바뀔지는 머름
    		
    		1. 구성 
    			초 분 시 일 월 요일 [년도] -- 순서대로 실행
    			
    		2. 상세
    			1) 초 : 0~59
    			2) 분 : 0~59
    			3) 시 : 0~23
    			3) 일 : 1~31
    			5) 월 : 0~11 또는 JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
    			6) 요일 : 1(MON)~7(SUN) 또는 MON, TUE, WED, THR, FRI, SAT, SUN
    		
    		3. 작성
    			1) * : 매번 // 일월요일쪽으로 많이 나옴
    			2) ? : 설정 없음(일, 요일에서 사용)
    			3) / : 주기
    			4) 		A/B : A부터 B마다 동작  // 초 단위에 0/1 하면 1초마다 라는 뜻. 
    		
    		4. 작성 예시 
    			1) 10초마다 매일   : 0/10 * * * * ? (어처피 매일 실행되기로 한거 요일 상관없으니까 무시하라는 뜻.그래서 물음표줫음)
    			2) 매일 1분 마다   : 0 0/1 * * * * ? 
    			3) 금,토 12시마다 : 0 0 12 ? * 5 FRI,SAT (일 은 지정할 수 없음 -- 수요일이 며칠인지 모르니껜)
    			4) 주말 12시마다 : 0 0 12 ? * SAT,SUN 
    			
    	
    	
    	
    	*/
    	
    	
         try {
        	 // Job 생성 (JobDetail : 쿼츠의 인터페이스) // class자체를 등록하는걸class타입
        	 JobDetail job = JobBuilder.newJob(StudentTop3Job.class)
        			 					.withIdentity("job1", "group1")
        	 							.build();
        	 // Trigger 생성
        	 Trigger trigger = TriggerBuilder.newTrigger()
        			 				.withIdentity("trigger1", "group1")
        			 				.startNow()
        			 				.withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")) // 30초 마다 
        			 				.build();
        	 
        	 // scheduler에 Job, Trigger 등록
        	 scheduler.scheduleJob(job, trigger);
        	 // scheduler 실행
        	 scheduler.start();
        	 
        	 // 스레드가 뭐더라.. 스레드가 별도로 동작해야되서 start임
         }catch(Exception e) {
        	 e.printStackTrace();
         }
    }
	
}
