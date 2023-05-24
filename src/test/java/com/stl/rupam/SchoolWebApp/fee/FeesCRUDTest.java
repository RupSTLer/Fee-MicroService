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


@SpringBootTest(classes = { FeesCRUDTest.class })
public class FeesCRUDTest {

	@Mock
	FeeRepo feeRepo;

	@InjectMocks
	FeeService feeService;

	// JUnit test for payFee
	@Test
	@Order(1)
	@Rollback(value = false)
	public void payFeeTest() {
		
		Fee fee = new Fee(3L, "SMS005", "Ritam", 5890L, "Cheque", "20-11-2023 02:14");
				
		when(feeRepo.save(fee)).thenReturn(fee);
		assertEquals(fee, feeService.payFees(fee));
	
//		assertNotNull(payFee);
	}

	// JUnit test for getFee
	@Test
	@Order(2)
	@Rollback(value = false)
	public void getFeesTest() {

		List<Fee> fees = new ArrayList<Fee>();

		fees.add(new Fee(3L, "SMS005", "Ritam", 5890L, "Cheque", "20-11-2023 02:14"));
		fees.add(new Fee(4L, "SMS006", "Ritam", 5899L, "Cash", "20-11-2023 02:14"));
		
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

		fees.add(new Fee(3L, "SMS005", "Ritam", 5890L, "Cheque", "20-11-2023 02:14"));
		fees.add(new Fee(4L, "SMS006", "Ritam", 5899L, "Cash", "20-11-2023 02:14"));

//		List<Fee> fees = Stream.of(new Fee(3L, "SMS005", "Ritam", 5890L, "Cheque", "20-11-2023 02:14"), new Fee(4L, "SMS006", "Ritam", 5899L, "Cash", "20-11-2023 02:14")).collect(Collectors.toList());

		when(feeRepo.findAll()).thenReturn(fees); // mocking

		assertEquals(2, feeService.listFees().size());

	}

	// JUnit test for updateFee
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateFeeTest() {
		Fee fees = Fee.builder().id(2L).studentId("SMS005").studentName("Rupam").amount(5760L).paymentType("Cash")
				.time("20-11-2023 02:14").build();

		fees.setStudentId("SMS002");

		feeService.updateFees(fees);

		Assertions.assertThat(fees.getStudentId()).isEqualTo("SMS002");

	}

}
