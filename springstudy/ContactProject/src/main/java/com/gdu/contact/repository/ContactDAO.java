package com.gdu.contact.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.contact.domain.ContactDTO;

@Repository
public class ContactDAO {
	
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","TIGER");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void close() {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(con != null) {con.close();}
		}catch(Exception e) { 
			e.printStackTrace();
		}
	}
	
	public List<ContactDTO> selectAllContacts(){
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();
		try {
			con=getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT ORDER BY NO DESC";
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				ContactDTO contact = new ContactDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				contacts.add(contact);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(); // con안끊고 db에 계속 접속된 상태로 냄겨둘 수 있는데 별로 안좋음
		}
		return contacts;
	}
	
	public int insertBoard(ContactDTO contact) {
		int result =0;
		
		
		
		return result;
	}
	
	
	
}
