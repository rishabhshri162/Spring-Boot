package com.rays.form;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.StudentDTO;

public class StudentForm extends BaseForm {

	@NotEmpty(message = "First is required")
	private String firstName;

	@NotEmpty(message = "Last is required")
	private String lastName;

	@NotNull(message = "Dob is required")
	private Date dob;

	@NotEmpty(message = "gender is required")
	private String gender;

	@NotEmpty(message = "Mob no is required")
	private String mobileNo;

	@NotEmpty(message = "email is required")
	private String email;

	@NotNull(message = "college id is required")
	private Long collegeId;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}


	@Override
	public BaseDTO getDto() {

		StudentDTO dto = new StudentDTO();

		dto = (StudentDTO) initDTO(dto);

		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setDob(dob);
		dto.setGender(gender);
		dto.setMobileNo(mobileNo);
		dto.setEmail(email);
		dto.setCollegeId(collegeId);

		return dto;
	}

}