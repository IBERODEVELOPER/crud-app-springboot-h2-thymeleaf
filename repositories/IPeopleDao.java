package com.ibero.web.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ibero.web.entities.People;

@Repository
public interface IPeopleDao {
	
	public List<People> findAll();
	public void savePeople(People people);
	public People findById(Integer id);
	public void deletePeopleById(Integer id);
}
