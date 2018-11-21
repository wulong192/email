package com.senqi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.senqi.entity.Pet;

public interface PetMapper {

	void save(Pet pet);

	List<Pet> getList(@Param("start")Integer start, @Param("end")Integer end);

}
