package ex05_;

public class RoomRobot extends Thread{

	
	
	private Cleaner cleaner;
	
	@Override
	public void run() {
		for( int i = 0; i<5; i++) {
		cleaner.roomCleaning();
		}
	}

	public RoomRobot(Cleaner cleaner) {
		super();
		this.cleaner = cleaner;
	}
	
	
	
}
