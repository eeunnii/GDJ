package domain;

import java.sql.Date;

//게시판 1개의 정보를 담을 수 있는 객체를 생성하기 위함 

//Bean, Vo, DTO 등으로 불림 

// ★테이블의 칼럼을 1:1로 변수로 매칭(가급적 칼럼이름 = 변수이름) 같은 타입으로!

//생성자, getter setter 만들어줌


public class Board {
	
	
	
	private int board_no;
	private String title;
	private String content;
	private int hit;
	private Date create_date;
	
	public Board(int board_no, String title, String content, int hit, Date create_date) {
		super();
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.create_date = create_date;
	}

	public Board() {
		super();
	}





	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
	
	
}
