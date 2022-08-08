package exam01;

public class Ex7_10{

public static void main(String[] args) {
	
	Unit[] group = new Unit[3];
	group[0] = new Marine();
	group[1] = new Tank();
	group[2] = new Dorpship();
	
	for (int i = 0; i<group.length; i++)
		group[i].move(100, 200);           
	//
	
	}
}
 abstract class Unit{
	
	int x, y;
	abstract void move(int x, int y); 
	void stop() { /*현재 위치에 정지*/}

}

 
 class Marine extends Unit {
	 void move(int x, int y) 	{ /* 지정된 위치로 이동 */ }
	 void stimPack()			{/*스팀팩을 사용한다. */}
 }
 
 class Tank extends Unit {
	 void move(int x, int y) 	{ /* 지정된 위치로 이동 (구현부)*/ }
	 void changMode()			{/* 공격모드로 변환한다. */}
 }
 
 class Dorpship extends Unit {
	 void move(int x, int y) 	{ /* 지정된 위치로 이동 (구현부)*/ }
	 void load()				{/* 선택된 대상을 태운다 */}
	 void unload()				{/* 선택된 대상을 내린다 */}
 }

 