package com.example.thymefreakingleap.repo;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.thymefreakingleap.model.Person2;

@Mapper()
public interface TestRepo {
	public List<HashMap> testSelect(@Param("chunk") String chunk);
	public List<HashMap> testSelect2(@Param("address") Person2.Address address);
}
