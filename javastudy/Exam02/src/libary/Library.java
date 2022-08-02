package libary;

import java.util.Scanner;

public class Library {
	
	private Scanner sc;				//선언만 하거임
	private Book2[] books;			//선언만 한거임
	
	
	public Library() {
		sc = new Scanner(System.in);
		books = new Book2[100];
	}
	
	
	public void manage() {
		while(ture) {
			System.out.print("1. 추가 2. 삭제. 3.조회. 4. 전체목록 0. 프로그램종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();   ///<엔터를 먹음.>//불필요한 엔터 ㅈ제거할 때 쓰는 방법 
			
			switch(choice) {
				case 1 : addBook; break;
				case 2 : removeBook(); break;
				case 3 : findBook(); break;
				case 4 : printAllBooks(); break;
				case 0 : System.out.println(" ");
						return; // 메소드 종료 
				default : System.out.println("알 수 없는 명령입니다. 어게인하세요");
				
			}
		}
	}

	private void addBook () {
		
		if (idx )
		
		System.out.println("====책등록====");
		System.out.println("제목 입력 >>> ");
		String title= sc.next();
		Book2 book = new Book2 (idx + 1, title, author);
		books[idx++]=book;
		
	}
	
	private void removeBook() {
		if (idx == 0 ) {
			System.out.println("등록된 채기 한 권도 없습니다.");
			return;
		}
		System.out.println("===책삭제===");
		System.out.println("삭제할 책의 번호(1~"+);
	}
	
	private void findBook() {
		if(idx ==0 ) {
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===책조회===");
		System.out.println("===조회할 책 제목 입력===");
		String title = sc.next();
		
		
		
	}
	
	
	
	private void printAllBooks () {
		
		if (idx==0) {
			System.out.println("등록된 책이 한권도 없습니다.");
			return;
		}
		System.out.println("==전체조회==");
		for(int i = 0; i<idx)
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
