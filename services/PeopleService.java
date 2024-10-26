package com.ibero.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibero.web.entities.People;
import com.ibero.web.repositories.IPeopleDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class PeopleService implements IPeopleDao{

	@PersistenceContext
	private EntityManager emManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<People> findAll() {
		return emManager.createQuery("from People").getResultList();
	}

	@Override
	@Transactional
	public void savePeople(People people) {
		if(people.getId() != null && people.getId() > 0) {
			emManager.merge(people);
		}else {
			emManager.persist(people);
		}
	}

	@Override
	@Transactional(readOnly = true) //Indica que es solo de lectura
	public People findById(Integer id) {
		return emManager.find(People.class, id);
	}

	@Override
	@Transactional
	public void deletePeopleById(Integer id) {
		People people = findById(id);
		emManager.remove(people);
	}

}
