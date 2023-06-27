package com.stl.rupam.SchoolWebApp.fee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
		
		Fee mockFee = new Fee(3L, "SMS001", "Monthly", "June", 999L, "20-11-2023 02:14");
				
		when(feeRepo.save(mockFee)).thenReturn(mockFee);   //mocking
		
		assertEquals("Fees paid successfully..", feeService.payFees(mockFee));
		verify(feeRepo, times(1)).save(mockFee);
	
	}

	// JUnit test for getFeesByStudentId
	@Test
	@Order(2)
	@Rollback(value = false)
	public void getFeesByStudentIdTest() {
		
		String studentId = "SMS002";
		
		List<Fee> mockFeeList = new ArrayList<Fee>();
		mockFeeList.add(new Fee(3L, studentId, "Monthly", "June", 999L, "20-11-2023 02:14"));
		mockFeeList.add(new Fee(3L, studentId, "Monthly", "July", 999L, "20-11-2023 02:14"));
		
		when(feeRepo.findByStudentId(studentId)).thenReturn(mockFeeList); // mocking
		
		List<Fee> mockService = feeService.getFeesByStudentId(studentId);
		
		assertEquals(mockFeeList, mockService);
		verify(feeRepo, times(1)).findByStudentId(studentId);

	}

//	
	// JUnit test for getAllFeeList
	@Test
	@Order(3)
	@Rollback(value = false)
	public void getListOfFeesTest() {

		List<Fee> mockFeeList = new ArrayList<Fee>();

		mockFeeList.add(new Fee(3L, "SMS001", "Monthly", "June", 999L, "20-11-2023 02:14"));
		mockFeeList.add(new Fee(3L, "SMS002", "Monthly", "July", 999L, "20-11-2023 02:14"));

		when(feeRepo.findAll()).thenReturn(mockFeeList); // mocking
		
		List<Fee> mockService = feeService.listFees();
		
		assertEquals(mockFeeList, mockService);
		verify(feeRepo, times(1)).findAll();

	}

	// JUnit test for updateFee
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateFeeTest() {
		
		Fee existingFees = new Fee(3L, "SMS002", "Monthly", "July", 999L, "20-11-2023 02:14");

		existingFees.setStudentId("SMS002");

		feeService.updateFees(existingFees);

		Assertions.assertThat(existingFees.getStudentId()).isEqualTo("SMS002");

	}

}


//Fee updateFees = new Fee(3L, "SMS001", "Ritam Chakraborty", 5890L, "Cash", "Monthly", "20-11-2023 02:14");
//
//when(feeRepo.saveAndFlush(existingFees)).thenReturn(updateFees);
//
////String mockService = feeService.updateFees(updateFees);
//
//assertEquals("Fees updated successfully..", feeService.updateFees(updateFees));
//verify(feeRepo, times(1)).saveAndFlush(existingFees);
