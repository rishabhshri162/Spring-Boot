package com.rays.ctl;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.dto.TestDTO;

@RestController
@RequestMapping(value = "Test")
public class TestCtl {
	
	@GetMapping("display")
	public String display() {
		
		return "display...";
	}
	
	@PostMapping("submit")
	public String submit() {
		
		return "submit!....";
		
		
	}
	
	@GetMapping("getDto")
	public TestDTO getdto() {
		
		TestDTO dto = new TestDTO();
		
		dto.setId(1);
		dto.setFirstName("Rishabh");
		dto.setLastName("Shrivastava");
		dto.setLogin("rs@gmail.com");
		dto.setPassword("ram");
		dto.setAddress("agra");
		dto.setDob(new Date());
		return dto;
		
		
	}

}
