package service;

import java.util.List;

import domain.ContactDTO;
import repository.ContactDAO;

public class ContactServieImpl implements ContactService {
	
	/******************Field*********************/
	
	//DAㅐ에 데이터를 전달하고, DAO로부터 결과를 반환 받기 위해서 DAO를 선언 
	private ContactDAO dao  =  ContactDAO.getInstance();
	
	
	
	

	@Override
	public void addContact(ContactDTO contact) {
		
		int result = dao.insertContact(contact);
		if(result>0) {
			System.out.println("연락처가 등록되었습니다.");
		}else {
			System.out.println("연락처 등록이 실패했습니다.");
		}

	}

	@Override
	public void modifyContact(ContactDTO contact) {
		// TODO Auto-generated method stub
		
		int result = dao.updateContact(contact);				
		///	마침표 뒤에 다 지운다음 . 옆에서 메소드 호출하기 
		if(result>0) {
			System.out.println("연락처가 수정되었습니다.");
		}else {
			System.out.println("연락처 수정이 실패했습니다.");
		}

	}

	@Override
	public void deleteContact(int contact_no) {
		// TODO Auto-generated method stub
		int result = dao.deleteContact(contact_no);
		if(result>0) {
			System.out.println("연락처가 삭제되었습니다.");
		}else {
			System.out.println("연락처 삭제가 실패했습니다.");
		}

	}

	@Override
	public void findContactByNo(int contact_no) {
		// TODO Auto-generated method stub
		
		ContactDTO contact = dao.selectContactByNo(contact_no);
		if(contact== null) {
			System.out.println("조회된 연락처가 없습니다.");
		}else {
			System.out.println(contact);
		}
		
		
		
		
		

	}

	@Override
	public void findALLContacts() {
		List<ContactDTO> contacts = dao.selectALLContactDTOs();
		if(contacts.isEmpty()) {
			System.out.println("저장된 연락처가 없습니다.");
		}else {
			for(ContactDTO contact : contacts) {
				System.out.println(contact);
			}
		}

	}

}
