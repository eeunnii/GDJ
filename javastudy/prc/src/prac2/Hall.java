package prac2;

import java.util.Arrays;
import java.util.Scanner;

public class Hall {



		private String hallName; // 홀 이름
		private SeatGroup[] seatGroups;
		private Scanner sc;
		private String[] seatType = {"S", "R", "A"};
		private int [] seatCount = {10, 10, 10};
		
		public Hall(String hallName) {
			this.hallName = hallName;
			seatGroups = new SeatGroup[seatType.length];         //s r a석이 있음을 의미함
			for(int i = 0; i<seatGroups.length; i++) {
				seatGroups[i] = new SeatGroup(seatType[i], seatCount[i]);
			}
		sc = new Scanner(System.in);
		
		}
		
		
		//예약 
		public void reserve() {
			System.out.println("좌석 타입 입력(S,R,A) >>> "+Arrays.toString);
			String seatType = sc.next();
			
			
			//선생님 깃허브에서 끌어오기
			
			for(int i = 0; i<seatGroups.length; i++) {
				if(seatType.equals(seatType[i]))	{
					seatGroups[i].se
				}
	
		}
		
		
		
		
		//예약 취소
		
		
		
		
		// 전체 예약 확인
			
			public void hallinfo() {
				System.out.println("["+hallName+"]");
				for(int i=0; i<seatGroups.length; i++) {
					seatGroups[i].reserveInfo();
				}
				System.out.println();
			}
		
		
		// 실행 
			
			public void mana
		
		
		
		
	}

}

