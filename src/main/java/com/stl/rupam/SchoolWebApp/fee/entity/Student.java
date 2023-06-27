package com.stl.rupam.SchoolWebApp.fee.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;



import lombok.Data;

@Entity
@Data
@Table(name = "students")
public class Student {
	
//	@NotEmpty(message = "student id is mandetory")
//	@Pattern(regexp = "^[SMS]{3}[0-9]{3}$", message = "please add valid ID")
	@Id
	private String studentId;
	
	@NotEmpty(message = "username is mandetory")
	@Pattern(regexp = "[a-zA-Z0-9]{4,}")
	private String userName;
	
	@NotEmpty(message = "password is mandetory")
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z]).{5,}")
	private String password;
	
	@NotEmpty(message = "student name is mandetory")
	@Pattern(regexp = "[a-zA-Z]{2}[a-zA-Z ]+", message = "please add valid name")
	private String name;
	
	@Email(message = "please give valid email")
	private String email;
	
}
