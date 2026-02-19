package com.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.RoleDTO;

@Repository
public class RoleDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(RoleDTO dto) {
		entityManager.persist(dto);
		return dto.getId();

	}

	public void update(RoleDTO dto) {
		entityManager.merge(dto);

	}

	public void delete(RoleDTO dto) {
		entityManager.remove(dto);

	}

	public RoleDTO findByPk(long pk) {
		RoleDTO dto = entityManager.find(RoleDTO.class, pk);
		return dto;

	}

	public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) {

		List<RoleDTO> list = null;

		
		// CriteriaBuilder: Query banane ke liye use karte hai
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		
		// CriteriaQuery: batata hai ki query kis type ka result degi (RoleDTO)
		CriteriaQuery<RoleDTO> cq = builder.createQuery(RoleDTO.class);

		
		// Root: entity class ko represent karta hai (FROM RoleDTO)
		Root<RoleDTO> root = cq.from(RoleDTO.class);

		cq.select(root);

		// EntityManager se select ki actual query create ho rahi hai
		TypedQuery<RoleDTO> tquery = entityManager.createQuery(cq);

		// limit 0, 5 pagination query append karne ke liye
		if (pageSize > 0) {
			tquery.setFirstResult(pageNo * pageSize);
			tquery.setMaxResults(pageSize);
		}

		list = tquery.getResultList();

		return list;
	}

}