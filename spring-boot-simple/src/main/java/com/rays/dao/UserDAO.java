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

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

@Repository
public class UserDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	RoleDAO roleDao;
	

	public void populate(UserDTO dto) {
		if (dto.getRoleId() != null && dto.getRoleId() > 0) {
			RoleDTO roleDto = roleDao.findByPk(dto.getRoleId());
			dto.setRoleName(roleDto.getName());
		}

	}

	public long add(UserDTO dto) {
		populate(dto);
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(UserDTO dto) {
		entityManager.merge(dto);

	}

	public void delete(UserDTO dto) {
		entityManager.remove(dto);
	}

	public UserDTO findByPk(long pk) {
		UserDTO dto = entityManager.find(UserDTO.class, pk);
		return dto;
	}

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {

		List<UserDTO> list = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		List<Predicate> pList = new ArrayList<Predicate>();

		Root<UserDTO> root = cq.from(UserDTO.class);

		if (dto != null) {
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				pList.add(builder.like(root.get("firstName"), dto.getFirstName() + "%"));

			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				pList.add(builder.like(root.get("lastName"), dto.getLastName() + "%"));

			}

		}
		cq.where(pList.toArray(new Predicate[pList.size()]));

//		cq.select(root);

		TypedQuery<UserDTO> tquery = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tquery.setFirstResult(pageNo * pageSize);
			tquery.setMaxResults(pageSize);

		}
		list = tquery.getResultList();
		return list;
	}

}
