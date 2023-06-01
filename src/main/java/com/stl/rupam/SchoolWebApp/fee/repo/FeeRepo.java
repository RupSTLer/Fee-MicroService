package com.stl.rupam.SchoolWebApp.fee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;

public interface FeeRepo extends JpaRepository<Fee, Long> {
	
	List<Fee> findByStudentId(String studentId);
	
	List<Fee> findByStudentIdAndFeeType(String studentId, String feeType);

}
