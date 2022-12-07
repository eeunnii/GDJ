package ex03_functional_interface.sec04;

public class Main {
	public static void main(String[] args) {
		
		Myinterface4 clac = (a,b)  ->  a+b;
		System.out.println(clac.add(1, 1));
		
		
		
	}
}
