package com.gdu.contact.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDTO {
	int no;
	String name;
	String tel;
	String adder;
	String email;
	String note;
}
