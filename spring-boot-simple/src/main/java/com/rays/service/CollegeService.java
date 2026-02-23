package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.CollegeDAO;
import com.rays.dto.CollegeDTO;
import com.rays.dto.UserDTO;

@Service
@Transactional
public class CollegeService {

	@Autowired
	CollegeDAO collegeDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(CollegeDTO dto) {
		long pk = collegeDao.add(dto);
		return pk;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		CollegeDTO dto = collegeDao.findByPk(id);
		collegeDao.delete(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(CollegeDTO dto) {
		collegeDao.update(dto);
	}

	@Transactional(readOnly = true)
	public CollegeDTO findByPk(long pk) {
		return collegeDao.findByPk(pk);
	}
	
	@Transactional(readOnly = true)
	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) {
		return collegeDao.search(dto, pageNo, pageSize);
	}

}
