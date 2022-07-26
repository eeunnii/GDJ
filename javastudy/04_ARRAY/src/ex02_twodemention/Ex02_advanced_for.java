package ex02_twodemention;

public class Ex02_advanced_for {
	
	////★1차원까지 공부해야함

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[][] timeTable = {
				{"국","수","영","탐"},
				{"미술", "수학","과학"},
				{"체육","수학","영어"},
				{"티비", "영어", "기타"},
				{"음악", "지리","이과"}	
		};
		
		for(int i = 0; i<timeTable.length; i++ ) {
			for(int j= 0; j<timeTable[i].length; j++) {
				System.out.print(timeTable[i][j]+" ");
			}
			System.out.println();
		}
		
		
		// 향상 for문
				for(String[] weekName : timeTable) {
					for(String course : weekName) {
						System.out.print(course + " ");
					}
					System.out.println();
				}
		}
		
		
	}


