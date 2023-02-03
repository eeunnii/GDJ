package ex07_contstruc;

public class ComputerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Computer myCom = new Computer("gram", 150);
		myCom.printComputerStatus();
		
		
		Computer yourCom = new Computer();     // 인수가 없는 생성자가 없어서 오류남
		yourCom.printComputerStatus();
		
		
		
	}

}
