package com.stl.rupam.SchoolWebApp.fee.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;

public interface FeeRepo extends JpaRepository<Fee, Long> {
	
//	Optional<Fee> findByStudentId(Long id);
	
	@Query(value = "select * from fees where student_id = ?", nativeQuery = true)
	Optional<Fee> findByStudentId(String studentId);

}
