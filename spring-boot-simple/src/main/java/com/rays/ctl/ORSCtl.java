package com.rays.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.TestDTO;

@RestController
@RequestMapping(value = "ors")
public class ORSCtl {

	@GetMapping
	public ORSResponse display() {

		ORSResponse res = new ORSResponse();

		return res;

	}

	@GetMapping("display1")
	public ORSResponse display1() { // For success and error message

		ORSResponse res = new ORSResponse();

		res.addMessage("User registered successfully");
		res.setSuccess(true);
		return res;

	}

	@GetMapping("display2")
	public ORSResponse display2() { // for input errors

		ORSResponse res = new ORSResponse();

		Map<String, String> errors = new HashMap<String, String>();

		errors.put("firstName", "first name is required");
		errors.put("lastName", "last name is required");
		errors.put("login", "login is required");
		errors.put("password", "password is required");

		res.addInputError(errors);

		return res;

	}

	@GetMapping("display3")
	public ORSResponse display3() { // for list

		List list = new ArrayList();

		ORSResponse res = new ORSResponse();

		TestDTO dto = new TestDTO();

		dto.setId(1);
		dto.setFirstName("Rishabh");
		dto.setLastName("Shrivastava");
		dto.setLogin("rs@gmail.com");
		dto.setPassword("12345");

		list.add(dto);

		TestDTO dto1 = new TestDTO();

		dto1.setId(2);
		dto1.setFirstName("Ram");
		dto1.setLastName("Shrivastava");
		dto1.setLogin("rs@gmail.com");
		dto1.setPassword("12345");

		list.add(dto1);

		res.addData(list);

		res.setSuccess(true);
		return res;

	}

	@GetMapping("display4")
	public ORSResponse display4() { // for key & value pair

		ORSResponse res = new ORSResponse();
		List roleList = new ArrayList();

		roleList.add("Admin");
		roleList.add("Student");
		roleList.add("Faculty");

		res.addResult("roleList", roleList);

		res.setSuccess(true);

		return res;

	}

}
