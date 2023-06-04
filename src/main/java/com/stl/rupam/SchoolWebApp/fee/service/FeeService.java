package com.stl.rupam.SchoolWebApp.fee.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;
import com.stl.rupam.SchoolWebApp.fee.entity.Student;
import com.stl.rupam.SchoolWebApp.fee.repo.FeeRepo;

@Service
public class FeeService {

	@Autowired
	private FeeRepo feeRepo;

	public String payFees(Fee fee) {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String timestamp = datetime.format(format);
		fee.setTime(timestamp);

		String s = validateFee(fee);

		if (s == null) {
			feeRepo.save(fee);
			return "Fees paid successfully..";
		} else {
			return s;
		}

	}

	private String validateFee(Fee fee) {
		
		try {

			List<Fee> existingPaidFees = feeRepo.findByStudentIdAndFeeType(fee.getStudentId(), fee.getFeeType());

			if (!existingPaidFees.isEmpty()) {
				throw new IllegalArgumentException("Fees already paid");
			}

			Student student = StudentRest.getStudentByStudentId(fee.getStudentId());

			if (student == null) {
				throw new IllegalArgumentException("Invalid student");
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}

		return null;
	}

	public String updateFees(Fee fee) {

		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String timestamp = datetime.format(format);
		fee.setTime(timestamp);

		String s = validateFee(fee);

		if (s == null) {
			feeRepo.saveAndFlush(fee);
			return "Fees updated successfully..";
		} else {
			return s;
		}
	}

	public List<Fee> getFeesByStudentId(String studentId) {
		return feeRepo.findByStudentId(studentId);
	}

	public List<Fee> listFees() {
		return feeRepo.findAll();
	}

	public Long countPaidFees() {
		return feeRepo.count();
	}
	
	public Fee getFeesDetails(String id) {
//		return feeRepo.findByStudentId(id).orElse(null);
		List<Fee> fees = feeRepo.findAll();
		Fee fee = null;

		for (Fee fee2 : fees) {
			if (fee2.getStudentId() == id) {
				fee = fee2;
			}
		}
		return fee;
	}

}
