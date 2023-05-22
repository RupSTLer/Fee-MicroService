package com.stl.rupam.SchoolWebApp.fee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "fees")
public class Fee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[SMS]{3}[0-9]{3}$", message = "please add valid ID")
	private String studentId;
	
	@Pattern(regexp = "[a-zA-Z]{2}[a-zA-Z ]+", message = "please add valid name")
	private String studentName;
	
//	@Pattern(regexp = "^[0-9]+[\\.]?[0-9]{0,2}$", message = "please add valid amount")
	@Positive(message = "value must be postitive")
	@Digits(integer=6, fraction=2)
	@DecimalMax(value = "100000", inclusive = false)
	private Long amount;
	
	private String paymentType;
	
	private String time;
	
}




//@Column(nullable = false)
//private Integer pendingMonths;
//@Column(nullable=false)
//private Integer clearedMonth;


//@Column(nullable=false)
//private Long paymentId;
