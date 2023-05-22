package com.stl.rupam.SchoolWebApp.fee.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;
import com.stl.rupam.SchoolWebApp.fee.repo.FeeRepo;

@Service
public class FeeService {
	
	@Autowired
	private FeeRepo feeRepo;
	
	public Fee payFees(Fee fee)
	{
		LocalDateTime datetime = LocalDateTime.now();  
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  
	    String timestamp = datetime.format(format);  
		fee.setTime(timestamp); 
		
		return feeRepo.save(fee);
	}
	
	public Fee updateFees(Fee fee) {
//		Fee x  = getFeesDetails(fee.getStudentId());
//		if(x == null) return repo.save(fee);
	
		return feeRepo.saveAndFlush(fee);
	}
	
	public Fee getFeesDetails(Long studentId) {
		return feeRepo.findByStudentId(studentId).orElse(null);
	}
	
	public List<Fee> listFees()
	{
		return feeRepo.findAll();
	}
	
	public Long countPaidFees() {
		return feeRepo.count();
	}
	
	
}

