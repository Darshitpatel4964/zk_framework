package com.crud.zkcrud.modelbean;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth; 
	private String mobile;
	private String address1;
	private String address2;
	private String age;
	private String gender;
	private String email;

}