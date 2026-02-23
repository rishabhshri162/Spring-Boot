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

import org.springframework.stereotype.Repository;

import com.rays.dto.CollegeDTO;

@Repository
public class CollegeDAO {

	@PersistenceContext
	EntityManager entityManager;

	public long add(CollegeDTO dto) {
		entityManager.persist(dto);
		return dto.getId();

	}

	public void update(CollegeDTO dto) {
		entityManager.merge(dto);

	}

	public void delete(CollegeDTO dto) {
		entityManager.remove(dto);

	}

	public CollegeDTO findByPk(long pk) {
		CollegeDTO dto = entityManager.find(CollegeDTO.class, pk);
		return dto;
	}

	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) {

		List<CollegeDTO> list = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<CollegeDTO> cq = builder.createQuery(CollegeDTO.class);

		List<Predicate> pList = new ArrayList<Predicate>();

		Root<CollegeDTO> root = cq.from(CollegeDTO.class);

		if (dto != null) {
			if (dto.getName() != null && dto.getName().length() > 0) {
				pList.add(builder.like(root.get("name"), dto.getName() + "%"));
			}
			if (dto.getAddress() != null && dto.getAddress().length() > 0) {
				pList.add(builder.like(root.get("address"), dto.getAddress() + "%"));
			}
			if (dto.getState() != null && dto.getState().length() > 0) {
				pList.add(builder.like(root.get("state"), dto.getState() + "%"));
			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				pList.add(builder.like(root.get("city"), dto.getCity() + "%"));
			}

		}
		cq.where(pList.toArray(new Predicate[pList.size()]));

		TypedQuery<CollegeDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}
		list = tq.getResultList();

		return list;

	}

}
