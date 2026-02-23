package com.rays.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.CollegeDTO;

public class CollegeForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Address is required")
	private String address;

	@NotEmpty(message = "state is required")
	private String state;

	@NotEmpty(message = "city is required")
	private String city;

	@NotEmpty(message = "phone no is required")
	private String phone_no;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	@Override
	public BaseDTO getDto() {
		CollegeDTO dto = new CollegeDTO();
		dto = (CollegeDTO) initDTO(dto);

		dto.setName(name);
		dto.setAddress(address);
		dto.setState(state);
		dto.setCity(city);
		dto.setPhone_no(phone_no);

		return dto;
	}

}
