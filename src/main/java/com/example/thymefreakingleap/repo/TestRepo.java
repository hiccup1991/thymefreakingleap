package com.example.thymefreakingleap.repo;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper()
public interface TestRepo {
	public List<HashMap> testSelect(@Param("chunk") String chunk);
}
