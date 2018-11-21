package com.senqi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senqi.entity.Pet;
import com.senqi.mapper.PetMapper;
import com.senqi.service.IPetService;

@Service
public class PetServiceImpl implements IPetService {

	@Autowired
	private PetMapper pm;
	
	
	public void save(List<Pet> list) {
		
		for (int i = 0; i < list.size(); i++) {
			
			pm.save(list.get(i));
			
		}
		
		
	}

	public List<Pet> getList(Integer start, Integer end) {
		return pm.getList(start - 1, end - start + 1);
	}
	
	
}
