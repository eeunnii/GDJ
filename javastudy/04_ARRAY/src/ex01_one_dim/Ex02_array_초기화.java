package ex01_one_dim;

public class Ex02_array_초기화 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//배열의 초기화
		//int[] arr = new int[] {10,20,30,40,50};
		int[]arr = {10,20,30,40,50};
		
		//최소/최대
		int max = arr[0];
		int min = arr[0]; // 초기값을 0으로 설정한뒤 실행하면 최소는 0이 나옴
		
		for(int i =1; i<arr.length; i++) {
			if (max<arr[i]) {
				max=arr[i];
			}
			if (min>arr[i]) {
				min = arr[i];
			}
		}
		
		System.out.println("최대 : "+max);
		System.out.println("최소 : "+min);
		
		
		
		
		
		
	}

}
