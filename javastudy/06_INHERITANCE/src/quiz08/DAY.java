package quiz08;

public class DAY {
	
	
	
	
	
	private String schedule;
	
	

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	
	public void printSchedule() {
		//스케줄이 없거나 빈 문자열이면 
		if(schedule == null || schedule.trim().isEmpty()) {
			System.out.println("x");
		} else {
			System.out.println(schedule);
		}
	}
	
	
	
	
	
	
	
	

}
