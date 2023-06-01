package com.stl.rupam.SchoolWebApp.fee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;
import com.stl.rupam.SchoolWebApp.fee.repo.FeeRepo;
import com.stl.rupam.SchoolWebApp.fee.service.FeeService;


@SpringBootTest(classes = { FeesServiceTest.class })
public class FeesServiceTest {

	@Mock
	FeeRepo feeRepo;

	@InjectMocks
	FeeService feeService;

	// JUnit test for payFee
	@Test
	@Order(1)
	@Rollback(value = false)
	public void payFeeTest() {
		
		Fee fee = new Fee(3L, "SMS001", "Ritam", 5890L, "Cheque", "Monthly", "20-11-2023 02:14");
				
//		when(feeRepo.save(fee)).thenReturn(fee);
		assertEquals("Fees paid successfully..", feeService.payFees(fee));
	
	}

	// JUnit test for getFee
	@Test
	@Order(2)
	@Rollback(value = false)
	public void getFeesTest() {

		List<Fee> fees = new ArrayList<Fee>();

		fees.add(new Fee(3L, "SMS005", "Ritam", 5890L, "Monthly", "Cheque", "20-11-2023 02:14"));
		fees.add(new Fee(4L, "SMS006", "Rupam", 5899L, "Yearly", "Cash", "20-11-2023 02:14"));
		
		String ID = "SMS005";
		
		when(feeRepo.findAll()).thenReturn(fees); // mocking
		
		assertEquals(ID, feeService.getFeesDetails(ID).getStudentId());

	}

//	
	// JUnit test for getFeeList
	@Test
	@Order(3)
	@Rollback(value = false)
	public void getListOfFeesTest() {

		List<Fee> fees = new ArrayList<Fee>();

		fees.add(new Fee(3L, "SMS005", "Ritam", 5890L, "Monthly", "Cheque", "20-11-2023 02:14"));
		fees.add(new Fee(4L, "SMS006", "Rupam", 5899L, "Yearly", "Cash", "20-11-2023 02:14"));

		when(feeRepo.findAll()).thenReturn(fees); // mocking

		assertEquals(2, feeService.listFees().size());

	}

	// JUnit test for updateFee
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateFeeTest() {

		Fee fees = new Fee(3L, "SMS005", "Ritam", 5890L, "Cheque", "Monthly", "20-11-2023 02:14");

		fees.setStudentId("SMS002");

		feeService.updateFees(fees);

		Assertions.assertThat(fees.getStudentId()).isEqualTo("SMS002");

	}

}
