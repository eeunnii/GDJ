package controller;

import java.util.Scanner;

import domain.ContactDTO;
import service.ContactService;
import service.ContactServieImpl;


//선택에ㅔ 따른 contactService의 메소드 호출

//contactService
//contactDAO의 메소드 호출


//controller -> serviec -> DAO -> DB (실제 개발 순서 . 역순으로 개발하면 빠름 )

public class ContactController {
	
	
	private Scanner sc;
	private ContactService contactService;  		// 변수 이름 시작은 소문자임. 조심하기 
	
	
	/************Consturtor *************/
	public ContactController() {				//	컨트롤+스페이스 기본 생성자 나옴 
		sc = new Scanner(System.in);
		contactService = new ContactServieImpl();  // 부모타입으로 자식결정 가능(업캐스팅)
	}

	/************Method*************/
	public void menu() {
		
		System.out.println("1.연락처등록");
		System.out.println("2.연락처수정");
		System.out.println("3.연락처삭제");
		System.out.println("4.연락처조회");
		System.out.println("5.전체연락처");
		System.out.println("0.종료");
	}
	
public void play()	{
	int contact_no;
	String name;
	String tel;
	String email;
	ContactDTO contact;
	while(true) {
		menu();
		System.out.println("선택(1~5) >>> ");
		int choice = sc.nextInt();		// 숫자는 choice에 저장
		sc.nextLine();							// 숫자 입력하고 누른 엔터 처리 
		switch(choice) {
		case 1 : 
			System.out.print("신규연락처 이름 >>> ");
			name = sc.next();
			System.out.print("신규연락처 전화번호  >>> ");
			tel = sc.next();
			System.out.print("신규연락처 이메일 >>> ");
			email = sc.next();
			contact = new ContactDTO();
			contact.setName(name);
			contact.setTel(tel);
			contact.setEmail(email);
			contactService.addContact(contact);
			break;
		case 2 : 
			System.out.println("수정할 연락처 번호 >>> ");
			contact_no = sc.nextInt();
			System.out.print("수정할 연락처 이름 >>> ");
			name = sc.next();
			System.out.print("수정할 연락처 전화번호  >>> ");
			tel = sc.next();
			System.out.print("수정할 연락처 이메일 >>> ");
			email = sc.next();			
			contact = new ContactDTO();
			contact.setContact_no(contact_no);
			contact.setName(name);
			contact.setTel(tel);
			contact.setEmail(email);
			contactService.modifyContact(contact);
			break;
		
		case 3 : 
			System.out.println("삭제할 연락처 번호 >>> ");
			contact_no = sc.nextInt();
			contactService.deleteContact(contact_no);
			break;
		case 4 : 
			System.out.println("조회할 연락처 번호 >>> ");			
			contact_no = sc.nextInt();
			contactService.findContactByNo(contact_no);	
			break;
		case 5 : 
			contactService.findALLContacts();
			break;
		case 0 : 
			System.out.println("연락처 프로그램을 종료합니다.");
			break;
		default : System.out.println("다시 선택하세요!");
		
		}
	}
		
}
	
}
