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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stl.rupam.SchoolWebApp.fee.entity.Fee;
import com.stl.rupam.SchoolWebApp.fee.service.FeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/fees")
@Api(tags = "Fee Service APIs", value = "Fee Controller", description = "it will handle the web requests of fee service")
public class FeeController {

	@Autowired
	private FeeService feesService;
	
	@ApiOperation(value = "Pay new Fees", notes = "returns a string when successfully paid")
	@PostMapping("/payFees")
	public String payFees(@Valid @RequestBody Fee fee)
	{
		return feesService.payFees(fee);
	}
	
	
	@ApiOperation(value = "update paid Fees details", notes = "returns a string when successfully updated")
	@PutMapping("/updateFee")
	public String updateFees(@Valid @RequestBody Fee fee) {
		return feesService.updateFees(fee);
	}
	
	@ApiOperation(value = "Get Fee details by studentID", notes = "returns a fee object")
	@GetMapping("/getFeesByStudentId/{studentId}")
	public List<Fee> getFeesByStudentId(@PathVariable String studentId) {
		return feesService.getFeesByStudentId(studentId);
	}
	
	@ApiOperation(value = "List all the paid Fees", notes = "returns a list of fees")
	@GetMapping("/listFees")
	public List<Fee> listFees()
	{
		return feesService.listFees();
	}
	
	@ApiOperation(value = "Count no of paid fees present in system", notes = "returns the fetched count of paid fees")
	@GetMapping("/countPaidFees")
	public ResponseEntity<Long> countPaidFees() {
		Long n1 = feesService.countPaidFees();
		return new ResponseEntity<Long>(n1, HttpStatus.OK);
	}
}


