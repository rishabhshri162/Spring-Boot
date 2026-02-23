package com.rays.common;

import java.sql.Timestamp;

public class BaseForm {

	protected Long id;

	private String created_by;

	private String modified_by;

	private Timestamp created_datetime;

	private Timestamp modified_datetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BaseDTO getDto() {
		return null;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Timestamp getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(Timestamp created_datetime) {
		this.created_datetime = created_datetime;
	}

	public Timestamp getModified_datetime() {
		return modified_datetime;
	}

	public void setModified_datetime(Timestamp modified_datetime) {
		this.modified_datetime = modified_datetime;
	}

	public BaseDTO initDTO(BaseDTO dto) {
		if (id != null && id > 0) {
			dto.setId(id);
		} else {
			dto.setId(null);
		}
		return dto;
	}

}