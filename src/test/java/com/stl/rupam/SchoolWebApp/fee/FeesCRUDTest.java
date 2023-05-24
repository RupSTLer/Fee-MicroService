//package com.stl.rupam.SchoolWebApp.fee;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import javax.transaction.Transactional;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.annotation.Rollback;
//
//import com.stl.rupam.SchoolWebApp.fee.entity.Fee;
//import com.stl.rupam.SchoolWebApp.fee.repo.FeeRepo;
//import com.stl.rupam.SchoolWebApp.fee.service.FeeService;
//
//
////@RunWith(SpringRunner.class)
////@DataJpaTest
////@Transactional
////@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//////@EnableAutoConfiguration(exclude=AutoConfigureTestDatabase.class)
////@AutoConfigureTestDatabase(replace= Replace.NONE)
//@SpringBootTest(classes = {FeesCRUDTest.class})
//public class FeesCRUDTest {
//	
//	@Mock
//	private FeeRepo feeRepo;
//	
//	@InjectMocks
//	private FeeService feeService;
//	
//	@Autowired
//	private FeeService feeService2;
//	
//	//JUnit test for payFee
//	@Test
//	@Order(1)
//	@Rollback(value = false)
//	public void payFeeTest()
//	{
//		Fee fee = Fee.builder()
//				.id(2L)
//				.studentId("SMS005")
//				.studentName("Rupam")
//				.amount(5760L)
//				.paymentType("Cash")
//				.time("20-11-2023 02:14")
//				.build();
//						
//		Fee payFee = feeService2.payFees(fee);
//		
////		Assertions.assertThat(fee.getId()).isGreaterThan(0);
//		
//		assertNotNull(payFee);
//	}
//	
//	//JUnit test for getFee
//	@Test
//	@Order(2)
//	@Rollback(value = false)
//	public void getFeesTest()
//	{
//		Fee fee = Fee.builder()
//				.id(2L)
//				.studentId("SMS005")
//				.studentName("Rupam")
//				.amount(5760L)
//				.paymentType("Cash")
//				.time("20-11-2023 02:14")
//				.build();
//		
//		List<Fee> fees = Stream.of(new Fee(2L, "SMS005", "Rupam", 5760L, "Cash", "20-11-2023 02:14")).collect(Collectors.toList());
//		
////		Fee fee2 = feeRepo.findById(2L).get();
////		feeService.getFeesDetails(fee.getId());
//		
////		System.out.println(feeService.getFeesDetails(fee.getStudentId()));
//		
////		Assertions.assertThat(feeService2.getFeesDetails(fee.getId())).isEqualTo(fees);
//		assertEquals(feeService2.getFeesDetails(fee.getId()),fees);
////		assertEquals(null, null);
//	}
////	
//	//JUnit test for getFeeList
//	@Test
////	@Order(3)
////	@Rollback(value = false)
//	public void getListOfFeesTest()
//	{
//		
//		List<Fee> fees = new ArrayList<Fee>();
//		
//		fees.add(new Fee(3L, "SMS005", "Ritam", 5890L, "Cheque", "20-11-2023 02:14"));
//		fees.add(new Fee(4L, "SMS006", "Ritam", 5899L, "Cash", "20-11-2023 02:14"));
//		
////		List<Fee> fees = Stream.of(new Fee(3L, "SMS005", "Ritam", 5890L, "Cheque", "20-11-2023 02:14"), new Fee(4L, "SMS006", "Ritam", 5899L, "Cash", "20-11-2023 02:14")).collect(Collectors.toList());
//		
//		when(feeRepo.findAll()).thenReturn(fees);
////		when(feeRepo.findAll()).thenReturn(Stream.of(new Fee(3L, "SMS005", "Ritam", 5890L, "Cheque", "20-11-2023 02:14"), new Fee(4L, "SMS006", "Ritam", 5899L, "Cash", "20-11-2023 02:14")).collect(Collectors.toList()));
////		Fee fee = Fee.builder()
////				.id(2L)
////				.studentId("SMS005")
////				.studentName("Rupam")
////				.amount(5760L)
////				.paymentType("Cash")
////				.time("20-11-2023 02:14")
////				.build();
////		
////		Fee fee2 = Fee.builder()
////				.id(2L)
////				.studentId("SMS005")
////				.studentName("Rupam")
////				.amount(5760L)
////				.paymentType("Cash")
////				.time("20-11-2023 02:14")
////				.build();
////		
////		feeRepo.save(fee);
////		feeRepo.save(fee2);
//				
////		assertEquals(fees, feeService.listFees());
//		assertEquals(2, feeService.listFees().size());
//		
////		System.out.println(feeRepo.findAll().size());
////				
////		Assertions.assertThat(feeService.listFees().size()).isEqualTo(2);
//	}
//	
//	//JUnit test for updateFee
//	@Test
//	@Order(4)
//	@Rollback(value = false)
//	public void updateFeeTest()
//	{
//		Fee fees = Fee.builder()
//				.id(2L)
//				.studentId("SMS005")
//				.studentName("Rupam")
//				.amount(5760L)
//				.paymentType("Cash")
//				.time("20-11-2023 02:14")
//				.build(); 
//				
//		fees.setStudentId("SMS002");
//				
//		feeService.updateFees(fees);
//		
//		Assertions.assertThat(fees.getStudentId()).isEqualTo("SMS002");
//		
//	}
//	
//
//}
//
