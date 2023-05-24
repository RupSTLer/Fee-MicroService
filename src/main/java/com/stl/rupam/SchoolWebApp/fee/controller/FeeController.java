package com.stl.rupam.SchoolWebApp.fee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;
import com.stl.rupam.SchoolWebApp.fee.service.FeeService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/fees")
public class FeeController {

	@Autowired
	private FeeService feesService;
	
	@PostMapping("/payFees")
	public Fee payFees(@Valid @RequestBody Fee fee)
	{
		return feesService.payFees(fee);
	}
	
	@PostMapping("/updateFee")
	public Fee updateFees(@Valid @RequestBody Fee fee) {
		return feesService.updateFees(fee);
	}
	
	@GetMapping("/getFeesDetails/{id}")
	public Fee getFeesDetails(@PathVariable String id) {
		return feesService.getFeesDetails(id);
	}
	
	@GetMapping("/listFees")
	public List<Fee> listFees()
	{
		return feesService.listFees();
	}
	
	@GetMapping("/countPaidFees")
	public ResponseEntity<Long> countPaidFees() {
		Long n1 = feesService.countPaidFees();
		return new ResponseEntity<Long>(n1, HttpStatus.OK);
	}
}


