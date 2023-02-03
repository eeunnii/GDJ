package ex05_;

public class ToiletRobot extends Thread {
	
	private Cleaner cleaner;
	
	@Override
	public void run() {
		for( int i = 0; i<5; i++) {			//청소하라고 하면 클리너를 5개씩 쓰는거임 
		cleaner.toiletCleaing();
		}
	}

	public ToiletRobot(Cleaner cleaner) {
		super();
		this.cleaner = cleaner;
	}
	
	
	
	

}
