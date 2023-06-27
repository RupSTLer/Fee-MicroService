package com.stl.rupam.SchoolWebApp.fee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;

public interface FeeRepo extends JpaRepository<Fee, Long> {
	
	List<Fee> findByStudentId(String studentId);
	
	List<Fee> findByStudentIdAndFeeType(String studentId, String feeType);
	
	List<Fee> findByStudentIdAndFeeTypeAndDuration(String studentId, String feeType, String duration);

	List<Fee> getByStudentIdAndFeeTypeAndDurationIn(String studentId, String feeType, List<String> duration);
	
	List<Fee> findByStudentIdAndDuration(String studentId, String duration);
	
	@Query(value = "select duration from fees where student_id = :studentId", nativeQuery = true)
	List<String> findExistingDurationbyStudentId(@Param("studentId") String studentId);
	
//	List<Fee> findIncompleteMonthlyFees(String studentId, String duration);

}
