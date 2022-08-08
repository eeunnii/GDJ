package ex03_hash;

//Book[] books -> List<Book> books
//배열을 클래스프레임워크로 변경할거임 
//
//Library.java
//addBook() - Full Check 없어짐 
//removeBook() - Empty Check 필요함 (예외처리로 변경)
//	removeBookIndex() - 인덱스 정보를 이용해서 제거함
//	removeObject() - 객체 정보를 이용해서 제거 
//findBook() - Empty Check 필요함 (예외처리로 변경)
//printAllBook() - Empty Check 필요함 (예외처리로 변경)
//
//*추가 
//modifyBook() -  책  제목을 입력 받아서 일치하는 책의 정보를 변경 



public class Book {
	private int bookNo;
	private String title;

	public Book(int bookNo, String title) {
		super();
		this.bookNo = bookNo;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookNo;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookNo != other.bookNo)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
