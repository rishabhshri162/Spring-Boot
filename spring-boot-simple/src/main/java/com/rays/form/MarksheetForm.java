package com.rays.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.MarksheetDTO;

public class MarksheetForm extends BaseForm {

	@NotEmpty(message = "roll no is required")
	private String rollNo;

	@NotEmpty(message = "student id is required")
	private Long studentId;

	@NotEmpty(message = "name is required")
	private String name;

	@NotEmpty(message = "physics is required")
	private Integer physics;

	@NotEmpty(message = "chemistry is required")
	private Integer chemistry;

	@NotEmpty(message = "maths is required")
	private Integer maths;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	@Override
	public BaseDTO getDto() {

		MarksheetDTO dto = new MarksheetDTO();

		dto = (MarksheetDTO) initDTO(dto);

		dto.setRollNo(rollNo);
		dto.setStudentId(studentId);
		dto.setName(name);
		dto.setPhysics(physics);
		dto.setChemistry(chemistry);
		dto.setMaths(maths);

		return dto;
	}

}
