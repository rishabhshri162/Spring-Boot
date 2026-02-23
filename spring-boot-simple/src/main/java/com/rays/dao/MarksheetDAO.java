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

import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;

@Repository
public class MarksheetDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	StudentDao studentDao;

	public void populate(MarksheetDTO dto) {
		StudentDTO sdto = studentDao.findByPk(dto.getStudentId());
		dto.setName(sdto.getFirstName());
	}

	public long add(MarksheetDTO dto) {
		populate(dto);
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(MarksheetDTO dto) {
		populate(dto);
		entityManager.merge(dto);
	}

	public void delete(MarksheetDTO dto) {

		entityManager.remove(dto);
	}

	public MarksheetDTO findByPk(long pk) {
		MarksheetDTO dto = entityManager.find(MarksheetDTO.class, pk);
		return dto;
	}

	public List<MarksheetDTO> search(MarksheetDTO dto, int pageNo, int pageSize) {

		List<MarksheetDTO> list = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<MarksheetDTO> cq = builder.createQuery(MarksheetDTO.class);

		List<Predicate> pList = new ArrayList<Predicate>();

		Root<MarksheetDTO> root = cq.from(MarksheetDTO.class);

		if (dto != null) {
			if (dto.getName() != null && dto.getName().length() > 0) {
				pList.add(builder.like(root.get("name"), dto.getName() + "%"));
			}

		}
		cq.where(pList.toArray(new Predicate[pList.size()]));

		TypedQuery<MarksheetDTO> tquery = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tquery.setFirstResult(pageNo * pageSize);
			tquery.setMaxResults(pageSize);

		}
		tquery.getResultList();
		return list;

	}

}
