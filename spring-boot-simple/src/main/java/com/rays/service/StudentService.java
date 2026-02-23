package com.rays.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.StudentDao;
import com.rays.dto.StudentDTO;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	StudentDao studentDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long add(StudentDTO dto) {
		long pk = studentDao.add(dto);
		return pk;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		StudentDTO dto = studentDao.findByPk(id);
		studentDao.delete(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(StudentDTO dto) {
		studentDao.update(dto);
	}

	@Transactional(readOnly = true)
	public StudentDTO findByPk(long pk) {
		return studentDao.findByPk(pk);
	}

	@Transactional(readOnly = true)
	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {
		return studentDao.search(dto, pageNo, pageSize);
	}

}
