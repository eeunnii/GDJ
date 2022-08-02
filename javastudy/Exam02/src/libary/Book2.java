package libary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor // new Book(1, "어린왕자", "생텍쥐베리")
@NoArgsConstructor	// new Book()

@Getter // getBookNo(), getTitle(), getAuthor()
@Setter //setBook(1), setTitle("어린왕자"), setAuthor("생텍쥐베리")


public class Book2 {
	
	
	
	

	
	
		private int bookNo;
		private String title;
		private String author;
		
		
		
	

}
