package quiz08;

import java.util.Scanner;

public class WeekScheduler {
	
	private int nthWeek;   // 1~n 주차
	private DAY[] week;
	private String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};
	private Scanner sc;
	
	public WeekScheduler(int nthWeek) {
		
//		요일 입력 >>> 수 
//		수요일에 있는 스케줄을 확인해서 
//		있으면 안받아주고 
//		없으면 받아주는 프로그램 만들기

		this.nthWeek = nthWeek;
		week = new DAY[7];
		sc = new Scanner(System.in);
		
	}
	
	private void makeSchedule() {
		System.out.println("등록");
		System.out.println("요일 입력 >>> ");
		String dayName = sc.next().substring(0, 1);         // 0부터 1전까지
		sc.nextLine();
		
		for(int i = 0; i<week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i]==null) {   // 등록된 스케줄이 없으면 
					System.out.println("스케줄 입력 >> ");
					String schedule = sc.nextLine();
					DAY day = new DAY();
					day.setSchedule(schedule);
					week[i] = day;
					System.out.println(dayName + "요일에 새 스케쥴이 등록되었습니다.");
				} else {
					System.out.println(dayName + "요일은 이미 스케줄이 있습니다.");
				}
				return;
			}
			
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");
	}
	
	
	
	
	
	private void changeSchedule() {
		System.out.println("변경할 요일 입력★");;
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		

		
	for (int i = 0; i<week.length; i++) {
		if(dayName.equals(dayNames[i])) {
			if(week[i]==null) {
				System.out.println(dayNames+"요일은 스케줄이 없습니다.");
				System.out.println("새 스케줄을 등록할까요? (y/n)");
				String yesNo = sc.next().substring(0, 1);
				sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						System.out.println("등록할 스케줄을 입력하세요.");
						String schedule = sc.nextLine();
						DAY day = new DAY();
						day.setSchedule(schedule);
						week[i]= day;
		
					} else {
						System.out.println("등록할 스케줄이 없습니다.");
						return;
					}
				
				
			} else {
				System.out.println(dayName+"의 스케줄은 "+week[i]+"입니다");
				System.out.println("변경할까요?(y/n)");
				String yesNo = sc.next().substring(0, 1);
					if(yesNo.equalsIgnoreCase("y")) {
						System.out.println("변경할 스케줄 내용을 입력하세요 >>>");
						String Schedule = sc.next();
						DAY day = new DAY();
						day.setSchedule(Schedule);
						week[i]= day;
						System.out.println(dayName+"의 스케줄이 변경되었습니다.");
						
					} else {
						System.out.println("알겠습니다");
						return;
					}
	
				
			}
			
		}
		System.out.println(dayName + "은 없는 요일입니다.");
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

		
	private void deleteSchedule() {
		
		
		
		System.out.println("삭제");
		System.out.println("삭제할 요일 입력 >>> ");
		String dayName = sc.next().substring(0,1);
		sc.nextLine();
		for(int i = 0; i<week.length; i++) {
			if (dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 스케줄이 없습니다");
				} else {
					System.out.println(dayName + "요일의 스케줄은"+week[i].getSchedule());
					System.out.println("삭제할까요 ? y/n?");
					String yesNo = sc.next().substring(0,1);
					sc.nextLine();
					if (yesNo.equalsIgnoreCase("y")) {
						week[i] = null;
						System.out.println(dayName + "요일의 스케줄이 취소되었습니다.");
					} else {
						System.out.println("스케줄 상태가 취소 되었습니다.,");
					}					
				}
				return;
			}
		}
		
		
		
		
	}
	
	private void printWeekSchedule() {
		
		System.out.println("전체조회");
		System.out.println(nthWeek + "주차 스케줄 안내");
		
		for(int i = 0; i <week.length; i++) {
			
			
			
			System.out.print(dayNames[i]+"요일 - ");
			System.out.println(week[i] == null ? "x" : week[i].getSchedule());
		
		}
	
	}
	
	public void manage() {
		
		
		
		while(true) {
			
			System.out.println("1. 등록 2. 수정 3. 삭제 4. 전체조회 0. 종료");
			int choice = sc.nextInt();
			sc.next();               // 엔터받았을 때 처리하는 방식
			
			switch(choice) {
			case 1 : makeSchedule(); break;
			case 2 : changeSchedule(); break;
			case 3 : deleteSchedule(); break;
			case 4 : printWeekSchedule(); break;
			case 0 : System.out.println("스케줄러를 종료합니다."); return;
			default : System.out.println("인식할 수 없는 명령입니다.");
			
			
			
			}
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
