package com.senqi.service;

import java.util.List;

import com.senqi.entity.Pet;

public interface IPetService {

	void save(List<Pet> list);

	List<Pet> getList(Integer start, Integer end);

	
}
