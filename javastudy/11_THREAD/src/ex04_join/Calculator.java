package ex04_join;

public class Calculator implements Runnable {

	private long total; // 필드값은 자동으로 초기화. 즉 0임
	private long begin;
	private long end;

	public Calculator(long begin, long end) {
		super();
		this.begin = begin;
		this.end = end;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void add() {

		for (long n = begin; n <= end; n++) {
			total += n;
		}

	}

	@Override
	public void run() {
		add();
	}

}
