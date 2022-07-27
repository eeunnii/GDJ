package ex05_method_overload;

public class Calculatormain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Calculator calculator = new Calculator()	;
		
		System.out.println(calculator.add(1,1));
		System.out.println(calculator.add(1,1,1));
		System.out.println(calculator.add(1,1,1,1));
		
		
		
		
		
		int[] arr = {1,2,3,4,5};
		//System.out.println(calculator.add(arr));
		
	}

}
