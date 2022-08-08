package quiz04;

public abstract class Employee {
	
	
	
	
	private int empNo; // 사번
	
	private String name;
	
	

	public Employee(int empNo, String name) { //@ALL~~
		super();
		this.empNo = empNo;
		this.name = name;
	}



	public int getEmpNo() {
		return empNo;
	}



	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

///////////////개터세터는 @Data 로 사용 가능함 ! (롬복)

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", name=" + name + "]";
	}
	
	//public Employee() { }  // @NOARGS~~~
	
	public abstract int getPay();
}
