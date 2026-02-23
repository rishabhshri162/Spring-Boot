package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.CollegeDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.StudentDTO;

@Repository
public class StudentDao {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	CollegeDAO collegeDao;

	public void populate(StudentDTO dto) {
		CollegeDTO collegeDto = collegeDao.findByPk(dto.getCollegeId());
		dto.setCollegeName(collegeDto.getName());

	}

	public long add(StudentDTO dto) {
		populate(dto);
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(StudentDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(StudentDTO dto) {
		entityManager.remove(dto);
	}

	public StudentDTO findByPk(long pk) {
		StudentDTO dto = entityManager.find(StudentDTO.class, pk);
		return dto;

	}

	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {

		List<StudentDTO> list = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<StudentDTO> cq = builder.createQuery(StudentDTO.class);

		List<Predicate> pList = new ArrayList<Predicate>();

		Root<StudentDTO> root = cq.from(StudentDTO.class);

		if (dto != null) {
			
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				pList.add(builder.like(root.get("name"), dto.getFirstName() + "%"));

			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				pList.add(builder.like(root.get("course"), dto.getEmail() + "%"));

			}

		}
		cq.where(pList.toArray(new Predicate[pList.size()]));
		
		TypedQuery<StudentDTO> tQuery = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tQuery.setFirstResult(pageNo * pageSize);
			tQuery.setMaxResults(pageSize);

		}
		tQuery.getResultList();
		return list;
	}

}
