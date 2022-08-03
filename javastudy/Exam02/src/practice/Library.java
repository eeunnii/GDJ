package practice;

import java.util.Scanner;

public class Library {
	
	
	private Scanner sc;
	private Book[] books;
	private int idx;            //
	
	
	
	public Library() {
		sc = new Scanner(System.in);
		books = new Book[100];
	}
	
	
	
	
	private void addBook() {
		
		if(idx == books.length) {
			System.out.println("더이상 등록할 수 없습니다.");
			return;
		}
		
		System.out.println("===책 제목===");
		System.out.println("제목 입력 >>> ");
		String title = sc.next();
		System.out.println("작가 입력 >>> ");
		String author = sc.next();
		Book book = new Book(idx+1, title, author); ////////idx + 1 은 왜 하는거지?
		books[idx++]=book;
		
	}
	
	
	
	
		private void removeBook() {
			
			
			if (idx == 0) {
				System.out.println("등록된 책이 한 권도 없습니다.");
				return;
			}
			
			
			System.out.println("===책 삭제===");
			System.out.println("삭제할 책의 번호 >>> ");
			int bookNo = sc.nextInt();
		
			
			
			for (int i = 0; i<books.length; i++) {
				if (bookNo == books[i].getBookNO()){
					System.arraycopy(, i, books, bookNo, i);
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
	
	
	
	
	
}
