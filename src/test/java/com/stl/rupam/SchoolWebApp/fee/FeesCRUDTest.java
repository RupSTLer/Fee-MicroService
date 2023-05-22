package com.stl.rupam.SchoolWebApp.fee;

import java.util.List;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;
import com.stl.rupam.SchoolWebApp.fee.repo.FeeRepo;


//@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@EnableAutoConfiguration(exclude=AutoConfigureTestDatabase.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class FeesCRUDTest {
	
	@Autowired
	private FeeRepo feeRepo;
	
	//JUnit test for save teacher
	@Test
	@Order(1)
	@Rollback(value = false)
	public void payFeeTest()
	{
		Fee fee = Fee.builder()
				.id(2L)
				.studentId("SMS005")
				.studentName("Rupam")
				.amount(5760L)
				.paymentType("Cash")
				.time("20-11-2023 02:14")
				.build();
				
		
		feeRepo.save(fee);
		
		Assertions.assertThat(fee.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
//	@Rollback(value = false)
	public void getFeeTest()
	{
		Fee fee = feeRepo.findById(2L).get();
		
		Assertions.assertThat(fee.getId()).isEqualTo(2L);
	}
	
	@Test
	@Order(3)
//	@Rollback(value = false)
	public void getListOfFeesTest()
	{
		
		List<Fee> fees = feeRepo.findAll();
		
		Assertions.assertThat(fees.size()).isEqualTo(2);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateFeeTest()
	{
		Fee fees = feeRepo.findById(2L).get();
		
		fees.setStudentId("SMS002");
		
		Fee feesUpdated = feeRepo.save(fees);
		
		Assertions.assertThat(feesUpdated.getStudentId()).isEqualTo("SMS002");
	}
	

}

