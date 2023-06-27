package com.stl.rupam.SchoolWebApp.fee.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
//		int q = LocalDate.now().get(IsoFields.QUARTER_OF_YEAR);
//		int q = LocalDate.now().get(IsoFields.DAY_OF_QUARTER);
//		System.out.println("yoo " + q);


		if (s == null) {
			feeRepo.save(fee);
			return "Fees paid successfully..";
		} else {
			return s;
		}
	}

	public String validateFee(Fee fee) {

		try {

			List<Fee> existingFees = feeRepo.findByStudentId(fee.getStudentId());
			List<Fee> existingPaidFees = feeRepo.findByStudentIdAndFeeTypeAndDuration(fee.getStudentId(),
					fee.getFeeType(), fee.getDuration());
			List<Fee> existingYearlyFees = feeRepo.findByStudentIdAndFeeType(fee.getStudentId(), "Yearly");
			List<Fee> existingMonthlylyFee = feeRepo.findByStudentIdAndFeeType(fee.getStudentId(), "Monthly");
			List<Fee> existingQuarterlyFee = feeRepo.findByStudentIdAndFeeType(fee.getStudentId(), "Quarterly");
			
			if (!existingPaidFees.isEmpty()) {
				throw new IllegalArgumentException("Fees already paid for " + fee.getDuration() + " "
						+ fee.getFeeType().substring(0, fee.getFeeType().length() - 2));
			}

			if ((!existingMonthlylyFee.isEmpty() || !existingQuarterlyFee.isEmpty()) && ("Yearly".equalsIgnoreCase(fee.getFeeType()))) {
				throw new IllegalArgumentException("Some Monthly and Quarterly fees already paid. Yearly fees not accepted");
			}
			
			if (!existingYearlyFees.isEmpty() && ("Monthly".equalsIgnoreCase(fee.getFeeType())
					|| "Quarterly".equalsIgnoreCase(fee.getFeeType()))) {
				throw new IllegalArgumentException("Yearly fees already paid. Monthly and Quarterly fees not accepted");
			}
			
//			if(!existingFees.isEmpty())
//			{
//				if(fee.getFeeType().equalsIgnoreCase("Monthly"))
//				{
//					boolean hasIncompleteMonthlyFees = existingFees.stream().anyMatch(existingFee -> existingFee.getFeeType().equalsIgnoreCase("Monthly") && !existingFee.isPaid());
//					
//					if(hasIncompleteMonthlyFees)
//					{
//						throw new IllegalArgumentException("Incomple monthly fees. Please pay the remaining monthly fees before opting for  quarter fees");
//					}
//				}
//			}

			if (fee.getFeeType().equalsIgnoreCase("Yearly")) {
				
				validateYearlyDuration(fee.getDuration());
				
			} else if (fee.getFeeType().equalsIgnoreCase("Quarterly")) {
				
//				validQuarterlySequence(fee.getDuration(), fee);

				List<String> month = getMonthsForQuarter(fee.getDuration());
				List<Fee> existingMonthlyFees = feeRepo.getByStudentIdAndFeeTypeAndDurationIn(fee.getStudentId(),
						"Monthly", month);

				if (!existingMonthlyFees.isEmpty()) {
					throw new IllegalArgumentException(
							"Monthly fees already paid for some months in the selected quarter. Quarterly fees not accepted");
				}
			} else if (fee.getFeeType().equalsIgnoreCase("Monthly")) {

				String quarter = getQuarterForMonth(fee.getDuration());

				List<Fee> existingQuarterlyFees = feeRepo.findByStudentIdAndFeeTypeAndDuration(fee.getStudentId(),
						"Quarterly", quarter);

				if (!existingQuarterlyFees.isEmpty()) {
					throw new IllegalArgumentException(
							"Quarterly fees already paid for the selected month. Monthly fees not accepted");
				}
				
				validMonthlySequence(fee.getDuration(), fee);
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

	public void validateYearlyDuration(String duration) {
		int currentYear = Year.now().getValue();

		int year;

		try {

			year = Integer.parseInt(duration);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid year");
		}

		if (year != currentYear) {
			throw new IllegalArgumentException("Invalid academic year");
		}
	}

	public String getQuarterForMonth(String month) {
		int monthIndex = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December").indexOf(month);

		int quarterIndex = (monthIndex / 3) + 1;

		if (monthIndex == -1 || quarterIndex < 1 || quarterIndex > 4) {
			throw new IllegalArgumentException("Invalid month");
		}

		String quarter = "Q" + quarterIndex;

		return quarter;

		// alternative implementation

//		Map<String, String> monthToQuarterMap = new HashMap<>();
//		
//		monthToQuarterMap.put("January", "Q1");
//		monthToQuarterMap.put("February", "Q1");
//		monthToQuarterMap.put("March", "Q1");
//		monthToQuarterMap.put("April", "Q2");
//		monthToQuarterMap.put("May", "Q2");
//		monthToQuarterMap.put("June", "Q2");
//		monthToQuarterMap.put("July", "Q3");
//		monthToQuarterMap.put("August", "Q3");
//		monthToQuarterMap.put("September", "Q3");
//		monthToQuarterMap.put("October", "Q4");
//		monthToQuarterMap.put("November", "Q4");
//		monthToQuarterMap.put("December", "Q4");
//		
//		String quarter = monthToQuarterMap.get(month);
//		
//		if(quarter == null)
//		{
//			throw new IllegalArgumentException("Invalid month");
//		}
//		
//		return quarter;

	}

	public List<String> getMonthsForQuarter(String quarter) {
		int quarterIndex = Integer.parseInt(quarter.substring(1));
		
		int startMonthIndex = (quarterIndex - 1)*3;
		
		List<String> allMonths = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
		
		int start = startMonthIndex;
		
		int end = startMonthIndex + 3;
		
		if(quarterIndex < 1 || quarterIndex > 4 || start < 0 || start >= allMonths.size() || end < 0 || end > allMonths.size())
		{
			throw new IllegalArgumentException("Invalid quarter");			
		}
		
		List<String> months = allMonths.subList(start, end);
		
		return months;
		
		
		// alternative implementation

//		List<String> months = new ArrayList<>();
//
//		switch (quarter) {
//		case "Q1":
//			months = Arrays.asList("January", "February", "March");
//			break;
//
//		case "Q2":
//			months = Arrays.asList("April", "May", "June");
//			break;
//
//		case "Q3":
//			months = Arrays.asList("July", "August", "September");
//			break;
//
//		case "Q4":
//			months = Arrays.asList("October", "November", "December");
//			break;
//
//		default:
//			throw new IllegalArgumentException("Invalid quarter");
//		}
//
//		return months;
	}

	public void validMonthlySequence(String month, Fee fee) {
		
		List<String> validSequence = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December");

		List<String> existingDurations = feeRepo.findExistingDurationbyStudentId(fee.getStudentId());

		if (!existingDurations.isEmpty()) {
			String lastMonth = existingDurations.get(existingDurations.size() - 1);
			int lastMonthIndex = validSequence.indexOf(lastMonth);
			int selectedMonthIndex = validSequence.indexOf(month);
			String selectedMonth = validSequence.get(selectedMonthIndex);
			int expectedNextMonthIndex = (lastMonthIndex + 1) % validSequence.size();
			String expectedMonth = validSequence.get(expectedNextMonthIndex);

			if (!selectedMonth.equalsIgnoreCase(expectedMonth)) {
				throw new IllegalArgumentException("Invalid month sequence for monthly fees. Expected month is - " + expectedMonth);
			}

//			if(selectedMonthIndex != expectedNextMonthIndex) {
//				throw new IllegalArgumentException("Invalid month sequence for monthly fees");
//			}

		}

	}

	public void validQuarterlySequence(String selectedQuarter, Fee fee)
	{		
		List<String> existingDurations = feeRepo.findExistingDurationbyStudentId(fee.getStudentId());
				
		if(!existingDurations.isEmpty())
		{
			String lastDuration = existingDurations.get(existingDurations.size() - 1);
			String lastQuarter = getLastQuarter(lastDuration);
		
			if(lastQuarter != null && !isNextQuarter(lastQuarter, selectedQuarter)) {
				throw new IllegalArgumentException("Invalid quarter sequence for Quarterly fees");
			}
		}
		
	}
		
	public String getLastQuarter(String duration)
	{
		return duration.matches("Q\\d") ? duration : null;
	}
	
	public boolean isNextQuarter(String lastQuarter, String selectedQuarter)
	{
//		List<String> validSequence = Arrays.asList("Q1", "Q2", "Q3", "Q4");
//		int lastQuarterIndex = validSequence.indexOf(lastQuarter);
//		int selectedQuarterIndex = validSequence.indexOf(selectedQuarter);
		int lastQuarterValue = Integer.parseInt(lastQuarter.substring(1));
		int selectedQuarterValue = Integer.parseInt(selectedQuarter.substring(1));
		
		
//		return selectedQuarterIndex == (lastQuarterIndex + 1)%validSequence.size();
		return selectedQuarterValue == lastQuarterValue + 1;
		
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
		List<Fee> fees = feeRepo.findByStudentId(studentId);
		sortFeesByTime(fees);
		return fees;
	}

	public List<Fee> listFees() {
		List<Fee> fees = feeRepo.findAll();
		sortFeesByTime(fees);
		return fees;
	}

	public void sortFeesByTime(List<Fee> fees) {
		Collections.sort(fees, new Comparator<Fee>() {

			@Override
			public int compare(Fee fee1, Fee fee2) {
				String time1 = fee1.getTime();
				String time2 = fee2.getTime();
				return time2.compareTo(time1);
			}
		});
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



