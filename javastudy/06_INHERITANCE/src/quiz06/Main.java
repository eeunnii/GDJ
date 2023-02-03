package quiz06;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 GameUnit unit1 = new Tank("탱크");
		 GameUnit unit2 = new Marine("마린");
		 
		
		unit1.info(); //탱크 에너지 100 공격력 10
		unit2.info(); // 마린 에너지 50 공격력 5
		
		
		System.out.println("===전투 시작===");
		
		// 공격 차례 정하기 
		boolean myTurn = Math.random()<0.5;
		
		
		//한대씩 주고 받기 
		// 두 유닛이 모두 살아있으면 계속 싸움
		while(unit1.isAlive() && unit2.isAlive()) { 
			//unit1.getEnergy() > 0 && unit.getEnergy() > 0
			
			
			if(myTurn) {
				System.out.println(unit1.getName() + "의 공격!");
				unit1.attack(unit2);  
				// unit1이 unit2를 공격한다. (탱크은 40%의 확률로 상대를 한 번에 죽인다.)
				myTurn = false ;
			}else {
				System.out.println(unit2.getName() + "의 공격!");
				unit2.attack(unit2);  
				// unit1이 unit2를 공격한다. (마린은 40%의 확률로 상대를 한 번에 죽인다.)
				myTurn = true ;
				
			}
			
			myTurn = !myTurn;			//공격 차례를 바꿔준다. 
			
		}
		
		System.out.println("===전투종료===");
		// 승자 확인
		if (unit1.isAlive()) {
			System.out.println(unit1.getName() + "의 승리! 남은에너지는 "+unit1.getEnergy());
			
		}else {
			System.out.println(unit2.getName() + "의 승리! 남은 에너지 " + unit2.getEnergy());
		}
		
		
	}

}
